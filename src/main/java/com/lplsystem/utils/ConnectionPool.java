package com.lplsystem.utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

public class ConnectionPool {
    //定义List合集，存放Connection对象
    private static List<Connection> list= new LinkedList<>();
    static {
        try {
            //把属性文件读取
            InputStream input = ConnectionPool.class.getResourceAsStream("/db.properties");
            if(input==null)System.out.println("inout is null");
            //创建Properties对象
            Properties properties =new Properties();
            //使用Properties对象读取属性文件并存储键值对
            properties.load(input);
            Class.forName(properties.getProperty("driver"));
            //产生10个Connection对象，并保存到list集合中，便于取用
            for(int i=0;i<10;i++){
                Connection conn = DriverManager.getConnection(
                        properties.getProperty("url"),
                        properties.getProperty("username"),
                        properties.getProperty("password"));
                list.add(conn);
            }
        } catch(ClassNotFoundException e){
            e.printStackTrace();
        } catch(SQLException | IOException e){
            e.printStackTrace();
        }
    }
    //获得Connection对象
    //方法加入synchronized修饰，代表加锁，谁是锁：当前类Connection的字节码对象：
    //调用时池子如果没有Connection对象则等待
    public synchronized static Connection getConnection(){
        //如果池子中都移除了，则没有对象
        if(list.size()==0){
            try{
                ConnectionPool.class.wait();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        //获取对象
        Connection conn=list.remove(0);
        return conn;
    }
    //把Conn对象放回池子
    public synchronized static void close(Connection connection,PreparedStatement pstmt,ResultSet rs){

        if(rs!=null){
            try {
                rs.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }

        if(pstmt!=null){
            try {
                pstmt.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        //关闭，放回池子
        list.add(connection);
        //随机唤醒一个线程
        ConnectionPool.class.notify();
    }

    /*
    如果一个线程调用了挪个对象的wait方法，那么该线程进入到该对象的等待池中（并且已经将锁释放），
    如果未来的某一时刻，另外一个线程调用了相同对象的notify方法，
    那么该等待池中的线程就会被唤起，然后进入到对象的锁池里面去获得该对象的锁，
    如果获得锁成功后，那么该线程就会沿着wait方法之后的路径继续执行，注意是沿着wait方法之后。
     */
}



package com.lplsystem.dao.impl;

import com.lplsystem.dao.UserDao;
import com.lplsystem.pojo.User;
import com.lplsystem.utils.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDaoImpl implements UserDao {
    @Override
    public User selectOneUser(String uname, String pwd) {

        //获取数据库连接
        Connection conn = ConnectionPool.getConnection();
        User user =null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        try {
            //预编译sql
            String sql = "select * from user where uname=? and pwd=?";
            ps = conn.prepareStatement(sql);
            //设置参数
            ps.setString(1, uname);
            ps.setString(2, pwd);
            //执行sql
            rs = ps.executeQuery();
            //处理结果集
            if(rs.next()){
                int uid = rs.getInt("uid");
                String uname1 = rs.getString("uname");
                String pwd1 = rs.getString("pwd");
                String realname = rs.getString("realname");
                int identity = rs.getInt("identity");
                //封住为User对象
                user=new User(uid,uname1,pwd1,realname,identity);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }finally {
            ConnectionPool.close(conn,ps,rs);
        }
        return user;
    }
}

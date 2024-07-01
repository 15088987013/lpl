package com.lplsystem.dao.impl;

import com.lplsystem.dao.PlayerDao;
import com.lplsystem.pojo.Player;
import com.lplsystem.pojo.Team;
import com.lplsystem.utils.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PlayerDaoImpl implements PlayerDao {

    @Override
    public int savePlayer(Player player) {
        //获取数据库连接
        Connection conn = ConnectionPool.getConnection();
        PreparedStatement ps = null;
        int i = 0;
        try {
            //预编译sql
            ps = conn.prepareStatement("insert into player values (default,?,?,?,?,?,?,?) ");
            //设置参数
            ps.setObject(1,player.getPname());
            ps.setObject(2,player.getHobby());
            ps.setObject(3,player.getPosition());
            ps.setObject(4,player.getBirthdate());
            ps.setObject(5,player.getPhone());
            ps.setObject(6,player.getPdesc());
            ps.setObject(7,player.getTid());
            //执行sql
            i = ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            ConnectionPool.close(conn,ps,null);
        }
        return i;
    }

    @Override
    public List<Player> selectAllPlayers(String name, String phone) {
        //获取数据库连接
        Connection conn = ConnectionPool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        //定义集合用于存放数据
        List<Player> list = new ArrayList<>();//记得初始化
        try {
            //预编译sql
            String sql = "select * from player p join team t on p.tid=t.tid where 1 = 1 ";
            if(!name.isEmpty()&&name!=null){
                sql+=" and p.pname like '%"+name+"%'";
            }
            if(!phone.isEmpty()&&phone!=null){
                sql+=" and p.phone like '%"+phone+"%'";
            }
            ps = conn.prepareStatement(sql);
            //执行sql
            rs = ps.executeQuery();
            //处理结果集，将数据封装为Team对象，添加到集合中
            while (rs.next()) {
                //学生信息
                int pid = rs.getInt("pid");
                String pname = rs.getString("pname");
                String position = rs.getString("position");
                String hobby = rs.getString("hobby");
                Date birthdate = rs.getDate("birthdate");
                String phone2 = rs.getString("phone");
                String pdesc = rs.getString("pdesc");
                //班级信息
                int tid = rs.getInt("tid");
                String tname = rs.getString("tname");
                String tlocation = rs.getString("tlocation");
                String tdesc = rs.getString("tdesc");
                //封装战队
                Team team = new Team(tid,tname,tlocation,tdesc);
                //封装选手
                Player player = new Player(pid,pname,position,hobby,birthdate,phone2,pdesc,tid,team);
                list.add(player);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            ConnectionPool.close(conn, ps, rs);
        }
        return list;
    }

    @Override
    public List<Player> selectAllPlayers(int startIndex, int size) {
        //获取数据库连接
        Connection conn = ConnectionPool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        //定义集合用于存放数据
        List<Player> list = new ArrayList<>();//记得初始化
        try {
            //预编译sql
            ps = conn.prepareStatement("select * from player limit ?,?");
            //设置参数
            ps.setInt(1,startIndex);
            ps.setInt(2,size);
            //执行sql
            rs = ps.executeQuery();
            //处理结果集，将数据封装为Player对象，添加到集合中
            while (rs.next()) {
                int pid = rs.getInt("pid");
                String pname = rs.getString("pname");
                String hobby = rs.getString("hobby");
                String position =  rs.getString("position");
                Date birthdate = rs.getDate("birthdate");
                String phone = rs.getString("phone");
                String pdesc = rs.getString("pdesc");
                int tid = rs.getInt("tid");
                Player player = new Player(pid,pname,hobby,position,birthdate,phone,pdesc,tid);
                list.add(player);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            ConnectionPool.close(conn, ps, rs);
        }
        return list;
    }

    @Override
    public List<Player> selectAllPlayers() {
        //获取数据库连接
        Connection conn = ConnectionPool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        //定义集合用于存放数据
        List<Player> list = new ArrayList<>();//记得初始化
        try {
            //预编译sql
            String sql = "select * from player ";
            ps = conn.prepareStatement(sql);
            //执行sql
            rs = ps.executeQuery();
            //处理结果集，将数据封装为Team对象，添加到集合中
            while (rs.next()) {
                //学生信息
                int pid = rs.getInt("pid");
                String pname = rs.getString("pname");
                String position = rs.getString("position");
                String hobby = rs.getString("hobby");
                Date birthdate = rs.getDate("birthdate");
                String phone2 = rs.getString("phone");
                String pdesc = rs.getString("pdesc");
                //班级信息
                int tid = rs.getInt("tid");
                String tname = rs.getString("tname");
                String tlocation = rs.getString("tlocation");
                String tdesc = rs.getString("tdesc");
                //封装战队
                Team team = new Team(tid,tname,tlocation,tdesc);
                //封装选手
                Player player = new Player(pid,pname,position,hobby,birthdate,phone2,pdesc,tid,team);
                list.add(player);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            ConnectionPool.close(conn, ps, rs);
        }
        return list;
    }

    @Override
    public int selectCount() {
        //获取数据库连接
        Connection conn = ConnectionPool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        int count = 0;
        try {
            //预编译sql
            ps = conn.prepareStatement("select count(*) from player ");
            //执行sql
            rs = ps.executeQuery();
            //处理结果集，将数据封装为Team对象，添加到集合中
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            ConnectionPool.close(conn, ps, rs);
        }
        return count;
    }

    @Override
    public int deletePlayer(int pid) {
        //获取数据库连接
        Connection conn = ConnectionPool.getConnection();
        PreparedStatement ps = null;
        int i = 0;
        try {
            //预编译sql
            ps = conn.prepareStatement("delete from player where pid = ? ");
            //设置参数
            ps.setInt(1,pid);
            //执行sql
            i = ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            ConnectionPool.close(conn, ps, null);
        }
        return i;
    }

    @Override
    public Player selectOnePlayer(int pid) {
        //获取数据库连接
        Connection conn = ConnectionPool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        Team team = null;
        Player player = null;
        try {
            //预编译sql
            ps = conn.prepareStatement("select * from player where pid = ? ");
            //设置参数
            ps.setInt(1,pid);
            //执行sql
            rs = ps.executeQuery();

            //处理结果集，将数据封装为Team对象
            if (rs.next()) {
//                int pid = rs.getInt("pid");
                String pname = rs.getString("pname");
                String hobby = rs.getString("hobby");
                String position =  rs.getString("position");
                Date birthdate = rs.getDate("birthdate");
                String phone = rs.getString("phone");
                String pdesc = rs.getString("pdesc");
                int tid = rs.getInt("tid");
                player = new Player(pid,pname,hobby,position,birthdate,phone,pdesc,tid);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            ConnectionPool.close(conn, ps, rs);
        }
        return player;
    }

    @Override
    public int updatePlayer(Player player) {
        //获取数据库连接
        Connection conn = ConnectionPool.getConnection();
        PreparedStatement ps = null;
        int i=0;
        try {
            //预编译sql
            ps = conn.prepareStatement("update player set pname = ? ,hobby = ? ,position = ? ,birthdate = ? , phone = ? ,pdesc = ? ,tid = ? where pid = ?");
            //设置参数
            ps.setString(1,player.getPname());
            ps.setString(2,player.getHobby());
            ps.setString(3,player.getPosition());
            ps.setDate(4,java.sql.Date.valueOf(player.getBirthdate().toString()));
            ps.setString(5,player.getPhone());
            ps.setString(6,player.getPdesc());
            ps.setInt(7,player.getTid());
            ps.setInt(8,player.getPid());
            //执行sql
            i = ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            ConnectionPool.close(conn, ps, null);
        }
        return i;
    }

    @Override
    public List<Player> selectAllPlayers(String name, String phone, int startIndex, int size) {
        //获取数据库连接
        Connection conn = ConnectionPool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        //定义集合用于存放数据
        List<Player> list = new ArrayList<>();//记得初始化
        try {
            //预编译sql
            String sql = "select * from player p join team t on p.tid=t.tid where 1 = 1 ";
            if(!name.isEmpty()&&name!=null){
                sql+=" and p.pname like '%"+name+"%' ";
            }
            if(!phone.isEmpty()&&phone!=null){
                sql+=" and p.phone like '%"+phone+"%' ";
            }
            sql += "  limit ?,? ";

            //预编译sql
            ps = conn.prepareStatement(sql);
            //设置参数
            ps.setInt(1,startIndex);
            ps.setInt(2,size);
            //执行sql
            rs = ps.executeQuery();
            //处理结果集，将数据封装为Team对象，添加到集合中
            while (rs.next()) {
                //学生信息
                int pid = rs.getInt("pid");
                String pname = rs.getString("pname");
                String position = rs.getString("position");
                String hobby = rs.getString("hobby");
                Date birthdate = rs.getDate("birthdate");
                String phone2 = rs.getString("phone");
                String pdesc = rs.getString("pdesc");
                //班级信息
                int tid = rs.getInt("tid");
                String tname = rs.getString("tname");
                String tlocation = rs.getString("tlocation");
                String tdesc = rs.getString("tdesc");
                //封装战队
                Team team = new Team(tid,tname,tlocation,tdesc);
                //封装选手
                Player player = new Player(pid,pname,position,hobby,birthdate,phone2,pdesc,tid,team);
                list.add(player);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            ConnectionPool.close(conn, ps, rs);
        }
        return list;
    }
}

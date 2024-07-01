package com.lplsystem.dao.impl;

import com.lplsystem.dao.TeamDao;
import com.lplsystem.pojo.Team;
import com.lplsystem.utils.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TeamDaoImpl implements TeamDao {

    @Override
    public int insertOneTeam(Team team) {
        //获取数据库连接
        Connection conn = ConnectionPool.getConnection();
        PreparedStatement ps = null;
        int i = 0;
        try {
            //预编译sql
            ps = conn.prepareStatement("insert into team values (default,?,?,?)");
            //设置参数
            ps.setString(1,team.getTname());
            ps.setString(2,team.getTlocation());
            ps.setString(3,team.getDesc());
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
    public List<Team> selectAllTeams() {
        //获取数据库连接
        Connection conn = ConnectionPool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        //定义集合用于存放数据
        List<Team> list = new ArrayList<>();//记得初始化
        try {
            //预编译sql
            ps = conn.prepareStatement("select * from team ");
            //执行sql
            rs = ps.executeQuery();
            //处理结果集，将数据封装为Team对象，添加到集合中
            while (rs.next()) {
                int tid = rs.getInt("tid");
                String tname = rs.getString("tname");
                String tlocation = rs.getString("tlocation");
                String desc = rs.getString("tdesc");
                Team team = new Team(tid,tname,tlocation,desc);
                list.add(team);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            ConnectionPool.close(conn, ps, rs);
        }
        return list;
    }

    @Override
    public List<Team> selectAllTeams(int startIndex, int size) {
        //获取数据库连接
        Connection conn = ConnectionPool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        //定义集合用于存放数据
        List<Team> list = new ArrayList<>();//记得初始化
        try {
            //预编译sql
            ps = conn.prepareStatement("select * from team limit ?,?");
            //设置参数
            ps.setInt(1,startIndex);
            ps.setInt(2,size);
            //执行sql
            rs = ps.executeQuery();
            //处理结果集，将数据封装为Team对象，添加到集合中
            while (rs.next()) {
                int tid = rs.getInt("tid");
                String tname = rs.getString("tname");
                String tlocation = rs.getString("tlocation");
                String desc = rs.getString("tdesc");
                Team team = new Team(tid,tname,tlocation,desc);
                list.add(team);
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
            ps = conn.prepareStatement("select count(*) from team ");
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
    public int deleteTeam(int tid) {
        //获取数据库连接
        Connection conn = ConnectionPool.getConnection();
        PreparedStatement ps = null;
        int i = 0;
        try {
            //预编译sql
            ps = conn.prepareStatement("delete from team where tid = ? ");
            //设置参数
            ps.setInt(1,tid);
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
    public Team selectOneTeam(int tid) {
        //获取数据库连接
        Connection conn = ConnectionPool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        Team team = null;
        try {
            //预编译sql
            ps = conn.prepareStatement("select * from team where tid = ?");
            //设置参数
            ps.setInt(1,tid);
            //执行sql
            rs = ps.executeQuery();
            //处理结果集，将数据封装为Team对象
            if (rs.next()) {
                String tname = rs.getString("tname");
                String tlocation = rs.getString("tlocation");
                String desc = rs.getString("tdesc");
                team = new Team(tid,tname,tlocation,desc);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            ConnectionPool.close(conn, ps, rs);
        }
        return team;
    }

    @Override
    public int updateTeam(Team team) {
        //获取数据库连接
        Connection conn = ConnectionPool.getConnection();
        PreparedStatement ps = null;
        int i=0;
        try {
            //预编译sql
            ps = conn.prepareStatement("update team set tname = ? ,tlocation = ? ,tdesc = ? where tid = ?");
            //设置参数
            ps.setString(1,team.getTname());
            ps.setString(2,team.getTlocation());
            ps.setString(3,team.getDesc());
            ps.setInt(4,team.getTid());
            //执行sql
            i = ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            ConnectionPool.close(conn, ps, null);
        }
        return i;
    }
}

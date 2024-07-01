package com.lplsystem.dao.impl;

import com.lplsystem.dao.CouchDao;
import com.lplsystem.pojo.Couch;
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

public class CouchDaoImpl implements CouchDao {

    @Override
    public int saveCouch(Couch couch) {
        //获取数据库连接
        Connection conn = ConnectionPool.getConnection();
        PreparedStatement ps = null;
        int i = 0;
        try {
            //预编译sql
            ps = conn.prepareStatement("insert into couch values (default,?,?,?,?,?,?,?)");
            //设置参数
            ps.setObject(1,couch.getCname());
            ps.setObject(2,couch.getCsex());
            ps.setObject(3,couch.getCjob());
            ps.setObject(4,couch.getBirthdate());
            ps.setObject(5,couch.getPhone());
            ps.setObject(6,couch.getCdesc());
            ps.setObject(7,couch.getTid());
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
    public List<Couch> selectAllCouch(String name, String phone) {
        //获取数据库连接
        Connection conn = ConnectionPool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        //定义集合用于存放数据
        List<Couch> list = new ArrayList<>();//记得初始化
        try {
            //预编译sql
            String sql = "select * from couch c join team t on c.tid=t.tid where 1 = 1 ";
            if(!name.isEmpty()&&name!=null){
                sql+=" and c.cname like '%"+name+"%'";
            }
            if(!phone.isEmpty()&&phone!=null){
                sql+=" and c.phone like '%"+phone+"%'";
            }
            ps = conn.prepareStatement(sql);
            //执行sql
            rs = ps.executeQuery();
            //处理结果集，将数据封装为Team对象，添加到集合中
            while (rs.next()) {
                //教练信息
                int cid = rs.getInt("cid");
                String cname = rs.getString("cname");
                String csex = rs.getString("csex");
                String cjob = rs.getString("cjob");
                Date birthdate = rs.getDate("birthdate");
                String phone2 = rs.getString("phone");
                String cdesc = rs.getString("cdesc");
                //班级信息
                int tid = rs.getInt("tid");
                String tname = rs.getString("tname");
                String tlocation = rs.getString("tlocation");
                String tdesc = rs.getString("tdesc");
                //封装战队
                Team team = new Team(tid,tname,tlocation,tdesc);
                //封装选手
                Couch couch = new Couch(cid,cname,csex,cjob,birthdate,phone2,cdesc,tid,team);
                list.add(couch);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            ConnectionPool.close(conn, ps, rs);
        }
        return list;
    }

    @Override
    public List<Couch> selectAllCouch(int startIndex, int size) {
        //获取数据库连接
        Connection conn = ConnectionPool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        //定义集合用于存放数据
        List<Couch> list = new ArrayList<>();//记得初始化
        try {
            //预编译sql
            ps = conn.prepareStatement("select * from couch limit ?,?");
            //设置参数
            ps.setInt(1,startIndex);
            ps.setInt(2,size);
            //执行sql
            rs = ps.executeQuery();
            //处理结果集，将数据封装为Player对象，添加到集合中
            while (rs.next()) {
                int cid = rs.getInt("cid");
                String cname = rs.getString("cname");
                String csex = rs.getString("csex");
                String cjob =  rs.getString("cjob");
                Date birthdate = rs.getDate("birthdate");
                String phone = rs.getString("phone");
                String cdesc = rs.getString("cdesc");
                int tid = rs.getInt("tid");
                Couch couch = new Couch(cid,cname,csex,cjob,birthdate,phone,cdesc,tid);
                list.add(couch);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            ConnectionPool.close(conn, ps, rs);
        }
        return list;
    }

    @Override
    public List<Couch> selectAllCouch() {
        //获取数据库连接
        Connection conn = ConnectionPool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        //定义集合用于存放数据
        List<Couch> list = new ArrayList<>();//记得初始化
        try {
            //预编译sql
            String sql = "select * from couch ";
            ps = conn.prepareStatement(sql);
            //执行sql
            rs = ps.executeQuery();
            //处理结果集，将数据封装为Team对象，添加到集合中
            while (rs.next()) {
                //教练信息
                int cid = rs.getInt("cid");
                String cname = rs.getString("cname");
                String csex = rs.getString("csex");
                String cjob = rs.getString("cjob");
                Date birthdate = rs.getDate("birthdate");
                String phone2 = rs.getString("phone");
                String cdesc = rs.getString("cdesc");
                //班级信息
                int tid = rs.getInt("tid");
                String tname = rs.getString("tname");
                String tlocation = rs.getString("tlocation");
                String tdesc = rs.getString("tdesc");
                //封装战队
                Team team = new Team(tid,tname,tlocation,tdesc);
                //封装选手
                Couch couch = new Couch(cid,cname,csex,cjob,birthdate,phone2,cdesc,tid,team);
                list.add(couch);
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
            ps = conn.prepareStatement("select count(*) from couch ");
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
    public int deleteCouch(int cid) {
        //获取数据库连接
        Connection conn = ConnectionPool.getConnection();
        PreparedStatement ps = null;
        int i = 0;
        try {
            //预编译sql
            ps = conn.prepareStatement("delete from couch where cid = ? ");
            //设置参数
            ps.setInt(1,cid);
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
    public Couch selectOneCouch(int cid) {
        //获取数据库连接
        Connection conn = ConnectionPool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        Team team = null;
        Couch couch = null;
        try {
            //预编译sql
            ps = conn.prepareStatement("select * from couch where cid = ? ");
            //设置参数
            ps.setInt(1,cid);
            //执行sql
            rs = ps.executeQuery();

            //处理结果集，将数据封装为Team对象
            if (rs.next()) {
//                int pid = rs.getInt("pid");
                String cname = rs.getString("cname");
                String csex = rs.getString("csex");
                String cjob =  rs.getString("cjob");
                Date birthdate = rs.getDate("birthdate");
                String phone = rs.getString("phone");
                String cdesc = rs.getString("cdesc");
                int tid = rs.getInt("tid");
                couch = new Couch(cid,cname,csex,cjob,birthdate,phone,cdesc,tid);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            ConnectionPool.close(conn, ps, rs);
        }
        return couch;
    }

    @Override
    public int updateCouch(Couch couch) {
        //获取数据库连接
        Connection conn = ConnectionPool.getConnection();
        PreparedStatement ps = null;
        int i=0;
        try {
            //预编译sql
            ps = conn.prepareStatement("update couch set cname = ? ,csex = ? ,cjob = ? ,birthdate = ? , phone = ? ,cdesc = ? ,tid = ? where cid = ?");
            //设置参数
            ps.setString(1,couch.getCname());
            ps.setString(2,couch.getCsex());
            ps.setString(3,couch.getCjob());
            ps.setDate(4,java.sql.Date.valueOf(couch.getBirthdate().toString()));
            ps.setString(5,couch.getPhone());
            ps.setString(6,couch.getCdesc());
            ps.setInt(7,couch.getTid());
            ps.setInt(8,couch.getCid());
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
    public List<Player> selectAllCouch(String name, String phone, int startIndex, int size) {
        //待定...
        return List.of();
    }
}

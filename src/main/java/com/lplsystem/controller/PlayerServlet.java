package com.lplsystem.controller;

import com.google.gson.Gson;
import com.lplsystem.pojo.Player;
import com.lplsystem.pojo.Team;
import com.lplsystem.service.PlayerService;
import com.lplsystem.service.impl.PlayerServiceImpl;
import com.lplsystem.utils.PageUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@WebServlet("/serve/PlayerServlet")
public class PlayerServlet extends HttpServlet {
    protected PlayerService playerService = new PlayerServiceImpl();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getParameter("method");
        if(method.equals("savePlayer")) {
            savePlayer(req,resp);
        }else if(method.equals("findAllPlayers")){
            findAllPlayers(req,resp);
        } else if (method.equals("findAllPlayers_page")) {
            findAllPlayers_page(req,resp);
        }else if (method.equals("deletePlayerByPid")) {
            deletePlayerByPid(req,resp);
        }else if (method.equals("findOnePlayer")) {
            findOnePlayer(req,resp);
        }else if (method.equals("updatePlayer")) {
            updatePlayer(req,resp);
        }else if (method.equals("findAllPlayers_list")) {
            findAllPlayers_list(req,resp);
        }else if (method.equals("findAllPlayersBySearch_page")) {
            findAllPlayersBySearch_page(req,resp);
        }
    }

    private void findAllPlayersBySearch_page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取前端传参：
        int page = Integer.parseInt(req.getParameter("page"));//string->int
        String pname = req.getParameter("pname");
        String phone = req.getParameter("phone");
        //定义一页展示几条数据
        int size = 8;
        //构建分页工具类的对象
        PageUtil pu = new PageUtil();
        pu.setSize(size);
        pu.setPage(page);
        int startIndex= pu.getStartIndex();//获取开始索引
        //访问Service层
        List<Player> list = playerService.findAllPlayer(pname,phone,startIndex,size);
        pu.setList(list);
        //设置数据一共有多少条、
        pu.setTotalCount(playerService.findCount());
        //设置一共多少页
        pu.setTotalPageCount();
        //设置页码数组：
        pu.setPageNums();
        //处理list：list对象->json字符串
        String json = new Gson().toJson(pu);
        //处理响应
        resp.getWriter().println(json);
    }

    private void findAllPlayers_page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        //获取前端传参：
        int page = Integer.parseInt(req.getParameter("page"));//string->int
        //定义一页展示几条数据
        int size = 8;
        //构建分页工具类的对象
        PageUtil pu = new PageUtil();
        pu.setSize(size);
        pu.setPage(page);
        int startIndex= pu.getStartIndex();//获取开始索引
        //访问Service层
        List<Player> list = playerService.findAllPlayer(startIndex,size);
        pu.setList(list);
        //设置数据一共有多少条、
        pu.setTotalCount(playerService.findCount());
        //设置一共多少页
        pu.setTotalPageCount();
        //设置页码数组：
        pu.setPageNums();
        //处理list：list对象->json字符串
        String json = new Gson().toJson(pu);
        //处理响应
        resp.getWriter().println(json);

    }

    private void savePlayer(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        //接收前台数据
        String pname = req.getParameter("pname");
        String position = req.getParameter("position");
        String hobby = Arrays.toString(req.getParameterValues("hobby")) ;
        Date birthdate = java.sql.Date.valueOf(req.getParameter("birthdate"));
        String phone = req.getParameter("phone");
        String pdesc = req.getParameter("pdesc");
        int tid = Integer.parseInt(req.getParameter("tid"));
        //封装
        Player player = new Player(0,pname, position, hobby, birthdate, phone, pdesc, tid);
        //调用Service
        int i = playerService.savePlayer(player);
        //响应数据
        resp.getWriter().println(i);
    }

    private void findAllPlayers(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{

        //接收前台数据
        String pname = req.getParameter("pname");
        String phone = req.getParameter("phone");
        //调用Service
        List<Player> list = playerService.findAllPlayer(pname,phone);
        //响应数据
        String json = new Gson().toJson(list);
        resp.getWriter().println(json);
    }

    private void deletePlayerByPid(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        //获取前段传过来的数据
        int pid = Integer.parseInt(req.getParameter("pid"));
        //调用Service层
        int i = playerService.deletePlayer(pid);
        //响应数据到前端
        resp.getWriter().println(i);
    }

    private void findOnePlayer(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        //1.获取前台请求数据
        int pid =Integer.parseInt(req.getParameter("pid")) ;
        //2.调用Service层
        Player player = playerService.findOnePlayer(pid);
        //3.将数据放入请求
        req.setAttribute("oneplayer",player);
        //4.处理响应 ,请求转发到修改页面
        req.getRequestDispatcher("/updatePlayer.jsp").forward(req,resp);
    }

    private void updatePlayer(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        //1.获取前台请求数据
        int pid =Integer.parseInt(req.getParameter("pid")) ;
        String pname = req.getParameter("pname");
        String hobby = req.getParameter("hobby");
        String position = req.getParameter("position");
        Date birthdate = java.sql.Date.valueOf(req.getParameter("birthdate"));
        String phone = req.getParameter("phone");
        String pdesc = req.getParameter("pdesc");
        int tid =Integer.parseInt(req.getParameter("tid")) ;
        //2.封装
        Player player = new Player(0,pname, position, hobby, birthdate, phone, pdesc, tid);
        //3.调用Service层
        int i = playerService.updatePlayer(player);
        //4.处理响应 ,请求转发到修改页面
        resp.getWriter().println(i);
    }

    private void findAllPlayers_list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{

        //接收前台数据
        String pname = req.getParameter("pname");
        String phone = req.getParameter("phone");
        //调用Service
        List<Player> list = playerService.findAllPlayer(pname,phone);

        //处理list：list对象->json字符串
        String json = new Gson().toJson(list);
        //处理响应
        resp.getWriter().println(json);
    }
}

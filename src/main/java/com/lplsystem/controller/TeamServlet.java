package com.lplsystem.controller;

import com.google.gson.Gson;
import com.lplsystem.pojo.Team;
import com.lplsystem.service.TeamService;
import com.lplsystem.service.impl.TeamServiceImpl;
import com.lplsystem.utils.PageUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/serve/TeamServlet")
public class TeamServlet extends HttpServlet {
    protected TeamService teamService = new TeamServiceImpl();
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getParameter("method");
        if (method.equals("save")) {
            save(req,resp);
        }else if (method.equals("findAllTeams")) {
            findAllTeams(req,resp);
        }else if (method.equals("findAllTeams_page")) {
            findAllTeams_page(req,resp);
        }else if (method.equals("deleteTeamByTid")) {
            deleteTeamByTid(req,resp);
        }else if (method.equals("findOneTeam")) {
            findOneTeam(req,resp);
        }else if (method.equals("updateTeam")) {
            updateTeam(req,resp);
        }else if (method.equals("findAllTeams_list")) {
            findAllTeams_list(req,resp);
        }
    }



    protected void save(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取前台请求数据
        String tname = req.getParameter("tname");
        String tloc = req.getParameter("tloc");
        String tdesc = req.getParameter("tdesc");
        //2.将数据封装为Team对象
        Team team = new Team(tname,tloc,tdesc);
        //3.调用Service层
        int i = teamService.saveTeam(team);
        //4.处理响应
        resp.getWriter().println(i);
    }

    protected void findAllTeams(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        //调用Service层
        List<Team> list = teamService.findAllTeams();
        //处理list：list对象->json字符串
        String json = new Gson().toJson(list);
        //处理响应
        resp.getWriter().println(json);
    }
    protected void findAllTeams_page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        //获取前端传参：
        int page = Integer.parseInt(req.getParameter("page"));//string->int
        //定义一页展示几条数据
        int size = 3;
        //构建分页工具类的对象
        PageUtil pu = new PageUtil();
        pu.setSize(size);
        pu.setPage(page);
        int startIndex= pu.getStartIndex();//获取开始索引
        //访问Service层
        List<Team> list = teamService.findAllTeams(startIndex,size);
        pu.setList(list);
        //设置数据一共有多少条、
        pu.setTotalCount(teamService.findCount());
        //设置一共多少页
        pu.setTotalPageCount();
        //设置页码数组：
        pu.setPageNums();
        //处理list：list对象->json字符串
        String json = new Gson().toJson(pu);
        //处理响应
        resp.getWriter().println(json);
        //测试：
//        System.out.println("size = "+size);
//        System.out.println("page = "+page);
//        System.out.println("startIndex = "+startIndex);
//        System.out.println("totalCount = "+teamService.findCount());
//        System.out.println("totalPageCount = "+pu.getTotalPageCount());
//        System.out.println(json);
//        int [] pageNums = pu.getPageNums();
//        System.out.println("pageNums length = "+pageNums.length);
//        System.out.print("pageNums = ");
//        for (int pageNum : pageNums) {
//            System.out.println(pageNum + "  ");
//        }
    }

    protected void deleteTeamByTid(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        //获取前段传过来的数据
        int tid = Integer.parseInt(req.getParameter("tid"));
        //调用Service层
        int i = teamService.deleteTeam(tid);
        //响应数据到前端
        resp.getWriter().println(i);
    }

    private void findOneTeam(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取前台请求数据
        int tid =Integer.parseInt(req.getParameter("tid")) ;
        //2.调用Service层
        Team team = teamService.findOneTeam(tid);
        //3.将数据放入请求
        req.setAttribute("oneteam",team);
        //4.处理响应 ,请求转发到修改页面
        req.getRequestDispatcher("/updateTeam.jsp").forward(req,resp);
    }

    private void updateTeam(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        //1.获取前台请求数据
        int tid =Integer.parseInt(req.getParameter("tid")) ;
        String tname = req.getParameter("tname");
        String tlocation = req.getParameter("tlocation");
        String desc = req.getParameter("desc");
        //2.封装
        Team team = new Team(tid,tname,tlocation,desc);
        //3.调用Service层
        int i = teamService.updateTeam(team);
        //4.处理响应 ,请求转发到修改页面
        resp.getWriter().println(i);
    }
    protected void findAllTeams_list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        //调用Service层
        List<Team> list = teamService.findAllTeams();
        //处理list：list对象->json字符串
        String json = new Gson().toJson(list);
        //处理响应
        resp.getWriter().println(json);
    }
}

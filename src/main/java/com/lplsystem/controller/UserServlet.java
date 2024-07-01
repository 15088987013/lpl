package com.lplsystem.controller;

import com.lplsystem.pojo.User;
import com.lplsystem.service.UserService;
import com.lplsystem.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/serve/UserServlet")
public class UserServlet extends HttpServlet {
    private UserService userService = new UserServiceImpl();
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //分支
        String method = req.getParameter("method");
        if ("login".equals(method)) {
            login(req, resp);
        }else if ("logout".equals(method)) {
            logout(req, resp);
        }
    }
    protected void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        //解决中文乱码问题（过滤器处理）
        //接受前端传送过来的数据
        String uname = req.getParameter("uname");
        String pwd = req.getParameter("pwd");
        String code = req.getParameter("code");
        //效验验证码
        if(req.getSession().getAttribute("randStr").equals(code)){
            //对数据做处理
            User user=userService.login(uname, pwd);
            //给前端页面做响应
            if(user != null){//证明数据查询成功
                resp.getWriter().println(1);
            }else {
                resp.getWriter().println(2);
            }
        }else{
            resp.getWriter().println(3);
            return ;//停止登录
        }

    }

    protected void logout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //退出：注销session
        req.getSession().invalidate();
        //跳转index.jsp(重定向)
        resp.sendRedirect(req.getContextPath()+"/login.jsp");
    }
}

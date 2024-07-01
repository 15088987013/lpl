package com.lplsystem.filter;


import javax.servlet.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;


public class EncodeFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain filterChain) throws IOException, ServletException, IOException {
        //设置请求信息的解码格式
        req.setCharacterEncoding("utf-8");
        //设置响应信息的编码格式
        resp.setCharacterEncoding("utf-8");
        //设置浏览器的解码格式
        resp.setContentType("text/html;charset=utf-8");
        //放行资源
        filterChain.doFilter(req,resp);
    }
}

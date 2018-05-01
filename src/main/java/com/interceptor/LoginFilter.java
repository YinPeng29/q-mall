package com.interceptor;


import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginFilter implements javax.servlet.Filter {
    public void init(FilterConfig filterConfig) throws ServletException {

    }


    /**
     * 过滤器设置 未登录无法查询 提示数据接口异常 安全处理
     * @param servletRequest
     * @param servletResponse
     * @param filterChain
     * @throws IOException
     * @throws ServletException
     */
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        System.out.println("过滤器: "+req.getRequestURL());
        Object user = req.getSession().getAttribute("user");
        if(user!=null){
            filterChain.doFilter(req,resp);
            return ;
        }
        System.out.println(req.getRequestURL());
        resp.sendRedirect("/login2.html");
        return ;
    }

    public void destroy() {

    }
}

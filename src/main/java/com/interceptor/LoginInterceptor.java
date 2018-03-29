package com.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor implements HandlerInterceptor {
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        System.out.println("最先进入拦截器，preHandle");
        System.out.println(request.getRequestURL());
        Object user = request.getSession().getAttribute("user");
        if(user !=null){
            return true;
        }
        response.sendRedirect("/login.html");
        return false;
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object o, ModelAndView modelAndView) throws Exception {
        System.out.println("modelAndView之前执行,postHandle");
    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object o, Exception e) throws Exception {
        System.out.println("执行完handler 完成执行此方法,afterCompletion");
    }
}

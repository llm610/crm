package com.powernode.controller.interceptor;

import com.powernode.beans.User;
import com.powernode.controller.LoginController;
import com.powernode.exception.MyException;
import com.powernode.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginInterceptor implements HandlerInterceptor {

    @Autowired
    UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        Object user = session.getAttribute(LoginController.SESSION_USER);

        System.out.println("LoginInterceptor............................");

        if (user==null){

            String username=null;
            String password=null;
            Cookie[] cookies = request.getCookies();
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(LoginController.SESSION_LOGINACT)){
                    username=cookie.getValue();
                }
                if (cookie.getName().equals(LoginController.SESSION_LOGINPWD)){
                    password=cookie.getValue();
                }
            }

            if (username!=null&&password!=null){
                User loginUser = null;
                try {
                    loginUser = userService.LoginUser(username, password, request.getRemoteAddr());
                }catch (MyException e){
                    System.out.println("自动登录:"+e.getMessage());
                    response.sendRedirect("/user/login");
                    return false;
                }

                session = request.getSession();
                session.setAttribute(LoginController.SESSION_USER,loginUser);
                return true;
            }

            response.sendRedirect("/user/login");
            return false;
        }
        else {
            return true;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}

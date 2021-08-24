package com.hao.interceptor;

import com.hao.entity.User;
import org.json.simple.JSONObject;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;



public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        //System.out.println("url="+request.getRequestURI());
        //获取session对象的user内容
        User user = (User) request.getSession().getAttribute("user");
        //System.out.println(user.getUsername());
        if (user == null) {
            //表示未登录或者登录状态失效
            System.out.println("未登录或登录失效，url = " + request.getRequestURI());
            if ("XMLHttpRequest".equals(request.getHeader("X-Requested-With"))) {
                //ajax请求
                Map<String, String> ret = new HashMap<String, String>();
                ret.put("type", "error");
                ret.put("msg", "登录状态已失效，请重新去登录!");
                response.getWriter().write(JSONObject.toJSONString(ret));
                return false;
            }
            //进行拦截跳转，你去登录
            response.sendRedirect(request.getContextPath() + "/system/login");
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}

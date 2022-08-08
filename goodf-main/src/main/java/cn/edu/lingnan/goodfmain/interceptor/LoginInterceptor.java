package cn.edu.lingnan.goodf.interceptor;

import cn.edu.lingnan.goodf.entity.UserEntity;

//import org.omg.PortableInterceptor.Interceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@Configuration
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String uri = request.getRequestURI();
        AntPathMatcher antPathMatcher = new AntPathMatcher();
        boolean match1 = antPathMatcher.match("/doctorWeb/toYuYue", uri);
        boolean match2 = antPathMatcher.match("/searchInfoByUserId", uri);

        if (match1 || match2){
            Object user = request.getSession().getAttribute("user");
            if (user != null) {
                return true;
            }
            else {
                request.getSession().setAttribute("msg","请先登录!");
                response.sendRedirect("http://localhost:10000/web/toLogin");
                return false;
            }
        }
        return true;


    }
}

package cn.sfcoder.aop;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: refain
 * @Description:
 * @Date: 2023/12/16 20:00
 * @Version: 1.0
 */
public class ImageInterceptor implements HandlerInterceptor {


    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {

        return true;
    }
}

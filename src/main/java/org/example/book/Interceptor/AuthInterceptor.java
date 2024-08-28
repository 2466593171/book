package org.example.book.Interceptor;

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.book.common.WebContext;
import org.example.book.dao.entity.User;
import org.example.book.util.RedisUtils;
import org.example.book.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;



@Component
public class AuthInterceptor implements HandlerInterceptor {

    @Autowired
    private TokenUtil tokenUtil;
    @Resource
    private WebContext webContext;

    @Autowired
    private RedisUtils redisUtils;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 从请求头中获取token
        String token = request.getHeader("Authorization");

        if (token != null && token.startsWith("Bearer")) {
            token = token.substring(7); // 去掉"Bearer "前缀

            // 验证token是否有效
            if (tokenUtil.validateToken(token)) {
                String username = tokenUtil.getUsernameFromToken(token);
                String id = tokenUtil.getIdFromToken(token);
                // 将用户信息存储在上下文中，供后续使用
                webContext.setCurrentUser(User.of().setUsername(username).setId(Long.valueOf(id)));

                return redisUtils.exists(username);
            }
        }
        response.setStatus(HttpServletResponse.SC_PAYMENT_REQUIRED); // 返回401未授权状态码
        return false;
    }
}

package com.example.utils;

import com.alibaba.fastjson.JSON;
import com.example.dto.UserHolder;
import com.example.pojo.User;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RefreshTokenInterceptor implements HandlerInterceptor {
    private final RedisUtil redisUtil;

    public  RefreshTokenInterceptor(RedisUtil redisUtil) {
        this.redisUtil = redisUtil;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //获取token
        String token = request.getHeader("authorization");
        //token为空
        if (IsStrNull.isnull(token)) return true;

        //获取key
        String key = RedisConstant.LOGIN_USER + token;

        //若用户不存在
        if (!redisUtil.hasKey(key)) {
            return true;
        }
        String userStr = redisUtil.get(key).toString();
        if (IsStrNull.isnull(userStr)) return  true;
        User user = JSON.parseObject(userStr, User.class);
        UserHolder.saveUser(user);
        //属性token过期时间
        redisUtil.expire(key,RedisConstant.LOGIN_USER_TTL);
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        //避免内存泄露
        UserHolder.removeUser();
    }
}

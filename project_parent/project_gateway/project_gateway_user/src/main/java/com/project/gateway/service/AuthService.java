package com.project.gateway.service;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpCookie;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class AuthService {
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    public String getJtiFromCookie(ServerHttpRequest request) {
        HttpCookie httpCookie = request.getCookies().getFirst("uid");
        if (httpCookie != null) {
            return httpCookie.getValue();
        }
        return null;
    }

    public String getJwtFromRedis(String jti) {
        return stringRedisTemplate.boundValueOps(jti).get();
    }
}

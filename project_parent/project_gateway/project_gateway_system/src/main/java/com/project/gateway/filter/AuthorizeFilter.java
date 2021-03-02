package com.project.gateway.filter;

import com.project.oauth.util.JwtUtil;
import io.jsonwebtoken.Claims;
import org.apache.commons.lang.StringUtils;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpCookie;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class AuthorizeFilter implements GlobalFilter, Ordered {
    public static final String LOGIN_URL = "http://localhost:8000/oauth/toLogin";

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        //获取请求对象
        ServerHttpRequest request = exchange.getRequest();
        //获得响应对象
        ServerHttpResponse response = exchange.getResponse();
        //判断是否是登录请求
        String path = request.getURI().getPath();
        if (path.contains("/oauth/login") || path.contains("/oauth/toLogin")) {
            //放行
            return chain.filter(exchange);
        }
        //方案1：增加请求头（请求头添加token:jwt）
        /*HttpHeaders headers = request.getHeaders();
        String jwtToken = headers.getFirst("token");*/
        //方案2：读取cookie中的令牌
        HttpCookie httpCookie = request.getCookies().getFirst("token");
        String jwtToken = null;
        if (httpCookie != null) {
            jwtToken = httpCookie.getValue();
        }
        if (StringUtils.isEmpty(jwtToken)) {
            //令牌不存在。返回错误信息
            /*response.setStatusCode(HttpStatus.UNAUTHORIZED);
            return response.setComplete();*/
            return toLoginPage(LOGIN_URL + "?FROM=" + request.getURI().getPath(), exchange);
        }
        //解析令牌
        Claims claims = null;
        try {
            claims = JwtUtil.parseJWT(jwtToken);
            System.out.println("claims = " + claims);
        } catch (Exception e) {
            e.printStackTrace();
            /*response.setStatusCode(HttpStatus.UNAUTHORIZED);
            return response.setComplete();*/
            return toLoginPage(LOGIN_URL + "?FROM=" + request.getURI().getPath(), exchange);
        }
        //增加请求头
        request.mutate().header("Token", jwtToken);
        return chain.filter(exchange);
    }

    //跳转登录页面
    private Mono<Void> toLoginPage(String loginUrl, ServerWebExchange exchange) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(HttpStatus.SEE_OTHER);
        response.getHeaders().set("Location", loginUrl);
        return response.setComplete();
    }

    @Override
    public int getOrder() {
        return 3;
    }
}

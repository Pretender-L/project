package com.project.web.filter;

import com.project.web.service.AuthService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class AuthFilter implements GlobalFilter, Ordered {

    @Autowired
    private AuthService authService;

    public static final String Authorization = "Authorization";

    public static final String LOGIN_URL = "http://localhost:9000/oauth/toLogin";

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();
        String path = request.getURI().getPath();
        //判断访问的是否是需要校验令牌的url
        if (!UrlFilter.hasAuthorize(path)) {
            return chain.filter(exchange);
        }
        String jti = authService.getJtiFromCookie(request);
        if (jti == null) {
            /*//拒绝访问（声明没有权限）
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            return response.setComplete();*/
            return toLoginPage(LOGIN_URL + "?FROM=" + request.getURI().getPath(), exchange);
        }
        String jwt = authService.getJwtFromRedis(jti);
        if (StringUtils.isEmpty(jwt)) {
            //拒绝访问（声明没有权限）
            /*response.setStatusCode(HttpStatus.UNAUTHORIZED);
            return response.setComplete();*/
            return toLoginPage(LOGIN_URL + "?FROM=" + request.getURI().getPath(), exchange);
        }
        //对请求头进行增强
        request.mutate().header(Authorization, "Bearer " + jwt);
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
        return 0;
    }

}

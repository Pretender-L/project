package com.project.system.filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.net.InetSocketAddress;

/**
 * 获得客户端访问的ip
 */
@Component
public class IpFilter implements GlobalFilter, Ordered {
    /***
     * 具体业务逻辑
     * @param exchange
     * @param chain
     * @return
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        InetSocketAddress remoteAddress = request.getRemoteAddress();
        System.out.println("IP:" + remoteAddress);
        return chain.filter(exchange);
    }

    /***
     * 过滤器执行优先级
     * @return
     */
    @Override
    public int getOrder() {
        return 1;
    }
}

/*
package com.angel.gateway.filter;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class JwtCheckGatewayFilterFactory extends AbstractGatewayFilterFactory<Object> {

    @Override
    public GatewayFilter apply(Object o) {
        return (exchange, chain) -> {
            //return chain.filter(exchange);
            String jwtToken = exchange.getRequest().getHeaders().getFirst("Authorization");
            //校验jwtToken的合法性
            */
/*if (JwtUtil.verifyToken(jwtToken) != null) {
                //合法
                return chain.filter(exchange);
            }*//*



            //不合法
            ServerHttpResponse response = exchange.getResponse();
            //设置headers
            HttpHeaders httpHeaders = response.getHeaders();
            httpHeaders.add("Content-Type", "application/json; charset=UTF-8");
            httpHeaders.add("Cache-Control", "no-store, no-cache, must-revalidate, max-age=0");
            //设置body
            JsonPackage jsonPackage = new JsonPackage();
            jsonPackage.setStatus(110);
            jsonPackage.setMessage("未登录或登录超时");
            DataBuffer bodyDataBuffer = response.bufferFactory().wrap(jsonPackage.toJSONString().getBytes());

            return response.writeWith(Mono.just(bodyDataBuffer));
        };
    }
}
*/

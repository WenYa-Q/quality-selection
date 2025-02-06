package com.wenya.filter;

import com.alibaba.fastjson2.JSONObject;
import com.wenya.quality.doamin.user.UserInfo;
import com.wenya.quality.vo.common.Result;
import com.wenya.quality.vo.common.ResultCodeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;

/**
 * auth全局筛选器
 * Description：
 *
 * @author wuqiulin
 */
@Slf4j
@Component
public class AuthGlobalFilter implements GlobalFilter, Ordered {

    @Autowired
    private RedisTemplate<String , String> redisTemplate;

    private AntPathMatcher antPathMatcher = new AntPathMatcher();

    /**
     * 过滤器
     *
     * @param exchange 交易所
     * @param chain    链条
     * @return {@link Mono }<{@link Void }>
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        String path = request.getURI().getPath();
        log.info("path:{}", path);

        UserInfo userInfo = this.getUserInfo(request);
        if (antPathMatcher.match("/api/**/auth/**", path)) {
            if (null == userInfo) {
                ServerHttpResponse response = exchange.getResponse();
                return out(response);
            }
        }
        
        return chain.filter(exchange);
    }

    /**
     * 输出
     *
     * @param response 响应
     * @return {@link Mono }<{@link Void }>
     */
    private Mono<Void> out(ServerHttpResponse response) {
        Result result = Result.build(null, ResultCodeEnum.LOGIN_AUTH);
        byte[] bits = JSONObject.toJSONString(result).getBytes(StandardCharsets.UTF_8);
        response.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
        return response.writeWith(Mono.just(response.bufferFactory().wrap(bits)));
    }

    /**
     * 获取用户信息
     *
     * @param request 请求
     * @return {@link UserInfo }
     */
    private UserInfo getUserInfo(ServerHttpRequest request) {
        String token = request.getHeaders().getFirst("token");
        if (StringUtils.hasText(token)) {
            String userInfoStr = redisTemplate.opsForValue().get("user:spzx:token:" + token);
            if (StringUtils.hasText(userInfoStr)) {
                return JSONObject.parseObject(userInfoStr, UserInfo.class);
            }
        }
        return null;
    }

    @Override
    public int getOrder() {
        return 0;
    }
}

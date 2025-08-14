package com.hy.filter;

import com.hy.result.ContentResult;
import com.hy.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import com.hy.result.Result;

import java.nio.charset.StandardCharsets;
import java.util.Set;

@Component  // 标记为Spring组件，纳入容器管理
@Order(-1)  // 过滤器执行优先级：值越小越先执行（-1表示优先级较高，在大部分过滤器之前执行）
public class LoginFilter implements GlobalFilter {  // 实现GlobalFilter接口，成为全局过滤器

    @Autowired
    private JwtUtil jwtUtil;  // 注入JWT工具类，用于解析和验证Token

    // 从配置文件中读取"免验证路径"，格式如：exclude.url=/login,/register/**
    // 解析逻辑：如果配置为空则设为null，否则按逗号分割为Set集合
    @Value("#{'${exclude.url}'.empty?null:'${exclude.url}'.split(',')}")
    private Set<String> urls;
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // 步骤1：获取请求相关对象
        ServerHttpRequest request = exchange.getRequest();  // 获取请求对象（包含路径、参数、请求头等）
        HttpHeaders headers = request.getHeaders();         // 获取请求头
        String token = headers.getFirst("token");           // 从请求头中获取名为"token"的字段值
        String path = request.getURI().getPath();           // 获取当前请求的路径（如/api/user/1）

        // 步骤2：判断当前请求是否为"免验证路径"
        AntPathMatcher antPathMatcher = new AntPathMatcher();  // Spring提供的路径匹配工具（支持通配符**）
        for(String url : urls) {
            // 若配置的免验证路径（如/login/**）与当前请求路径匹配，则直接放行
            if(antPathMatcher.match(url, path)) {
                return chain.filter(exchange);  // 放行：继续执行过滤器链，最终转发到目标服务
            }
        }

        // 步骤3：非免验证路径，需要验证Token
        // 情况1：请求头中没有Token（token为null）
        if(token == null) {
            return getMono(exchange);  // 返回"Token无效"的错误响应
        }

        // 情况2：有Token，验证其有效性
        try {
            // 调用JwtUtil解析Token（内部会验证签名和过期时间）
            jwtUtil.parseJwt(token);
            // 解析成功→Token有效，放行
            return chain.filter(exchange);
        } catch (Exception e) {
            // 解析失败（如签名错误、Token过期等）→Token无效
            e.printStackTrace();
            return getMono(exchange);  // 返回"Token无效"的错误响应
        }
    }

    private Mono<Void> getMono(ServerWebExchange exchange) {
        ServerHttpResponse response = exchange.getResponse();  // 获取响应对象

        // 设置响应头：指定返回格式为JSON，编码为UTF-8
        response.getHeaders().add("Content-Type", "application/json;charset=UTF-8");

        // 构建错误结果对象（使用自定义的Result和ContentResult）
        // 状态码和消息来自ContentResult中的常量（TOKEN_NOT_NULL_CODE="4"，TOKEN_NOT_NULL_MESSAGE="token无效"）
        Result result = new Result(ContentResult.TOKEN_NOT_NULL_CODE, ContentResult.TOKEN_NOT_NULL_MESSAGE);

        // 将Result对象转为JSON字符串的字节数组，写入响应并返回
        // 注：这里假设Result类的toString()方法会返回JSON格式字符串（实际可能需要用Jackson等工具序列化）
        return response.writeWith(
                Mono.just(response.bufferFactory().wrap(result.toString().getBytes(StandardCharsets.UTF_8)))
        );
    }
}

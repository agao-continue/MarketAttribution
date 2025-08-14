package com.hy.utils;

import io.jsonwebtoken.Claims;       // JWT载荷信息封装类
import io.jsonwebtoken.JwtBuilder;   // JWT构建器，用于组装JWT各个部分
import io.jsonwebtoken.Jwts;         // JWT核心工具类，提供生成和解析JWT的方法
import io.jsonwebtoken.SignatureAlgorithm;  // 签名算法枚举类
import org.springframework.stereotype.Component;
import java.util.Date;  // 日期类，用于设置JWT的签发时间和过期时间

/**
 * JWT（JSON Web Token）工具类
 * 功能：生成JWT令牌、解析JWT令牌，用于在分布式系统中实现无状态身份验证
 * 核心原理：通过数字签名保证令牌不被篡改，避免服务端存储会话状态
 */
@Component
public class JwtUtil {
    /**
     * 签名密钥（核心安全要素）
     * 作用：用于JWT的签名和验证，确保令牌在传输过程中未被篡改
     * 注意：
     * 1. 密钥必须保密，生产环境需使用复杂字符串（建议16位以上，包含大小写、数字、特殊字符）
     * 2. 对称加密算法（如HS256）中，生成和解析令牌的服务必须使用相同密钥
     */
    private String key = "huayu";  // 此处为演示密钥，实际使用需加强安全性

    /**
     * JWT过期时间（单位：毫秒）
     * 计算逻辑：30天 = 30L * 24小时 * 60分钟 * 60秒 * 1000毫秒
     * 作用：控制令牌的有效期，过期后令牌失效，需要重新获取
     */
    private long ttl = 30L * 24 * 60 * 60 * 1000;

    /**
     * 生成JWT令牌
     *
     * @param id      令牌唯一标识（可选，如用户ID、请求ID等，用于区分令牌）
     * @param subject 主题信息（必填，通常存放核心身份信息，如用户名、用户ID）
     * @param roles   自定义载荷（可选，此处存放用户角色，用于权限控制）
     * @return 生成的JWT令牌字符串（格式：Header.Payload.Signature）
     */
    public String createJwt(String id, String subject, String roles) {
        // 1. 获取当前时间戳（毫秒），用于计算签发时间和过期时间
        long millis = System.currentTimeMillis();
        // 2. 创建签发时间对象（JWT的iat字段）
        Date date = new Date(millis);
        // 3. 构建JWT（核心步骤）
        JwtBuilder jwtBuilder = Jwts.builder()
                .setId(id)  // 设置jti（JWT ID）：令牌唯一标识
                .setSubject(subject)  // 设置sub（Subject）：主题信息
                .setIssuedAt(date)  // 设置iat（Issued At）：签发时间
                // 设置签名算法和密钥：使用HS256对称加密算法，通过key签名
                .signWith(SignatureAlgorithm.HS256, key)
                // 添加自定义载荷：通过claim方法添加额外信息（此处为用户角色）
                .claim("roles", roles);
        // 4. 设置过期时间（如果ttl>0，则添加exp字段）
        if (ttl > 0) {
            // 过期时间 = 签发时间 + 有效期（ttl）
            jwtBuilder.setExpiration(new Date(millis + ttl));
        }
        // 5. 压缩为最终的JWT字符串（三部分用.拼接）
        return jwtBuilder.compact();
    }
    /**
     * 解析JWT令牌，获取载荷信息
     *
     * @param jwtStr 待解析的JWT令牌字符串
     * @return Claims对象（包含所有载荷信息，可通过get方法获取具体字段）
     * @throws （解析失败会抛出异常如ExpiredJwtException：令牌过期；SignatureException：签名错误）
     */
    public Claims parseJwt(String jwtStr) {
        // 解析逻辑：
        // 1. 通过Jwts.parser()创建解析器
        // 2. 设置签名密钥（必须与生成时的key一致，否则验证失败）
        // 3. 调用parseClaimsJws(jwtStr)解析令牌（自动验证签名和过期时间）
        // 4. 通过getBody()获取载荷信息（Claims对象）
        return Jwts.parser().setSigningKey(key).parseClaimsJws(jwtStr).getBody();
    }
    public String getKey() {
        return key;
    }
    public void setKey(String key) {
        this.key = key;
    }
    public long getTtl() {
        return ttl;
    }
    public void setTtl(long ttl) {
        this.ttl = ttl;
    }
}
package com.springtest.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtTokenUtil {

    // 使用一个固定的密钥生成 SecretKey，避免每次启动时重新生成
    private static final SecretKey SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    // 生成 Token
    public String generateToken(String username) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, username);
    }

    // 创建 Token 的方法
    private String createToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // 设置过期时间 (10小时)
                .signWith(SECRET_KEY, SignatureAlgorithm.HS256) // 使用 HS256 算法签名
                .compact();
    }

    // 验证 Token 是否过期
    public static boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    // 从 Token 中提取用户名
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    // 提取 Token 中的过期时间
    public static Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    // 提取任意声明
    public static <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractClaims(token);
        return claimsResolver.apply(claims);
    }

    // 解析 Token
    private static Claims extractClaims(String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(SECRET_KEY)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            // 可以考虑记录日志
            return null; // 验证失败时返回 null
        }
    }

    // 验证 Token 的有效性并返回 Claims
    public static Claims validateToken(String token) {
        Claims claims = extractClaims(token);
        if (claims != null && !isTokenExpired(token)) {
            return claims; // Token 有效
        }
        return null; // Token 无效
    }
}

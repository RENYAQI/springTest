package com.springtest.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtTokenUtil {

    private final SecretKey SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    // 生成 token
    public String generateToken(String username) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, username);
    }

    // 创建 token 的方法
    private String createToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .setClaims(claims) // 自定义的载荷
                .setSubject(subject) // token 的主体，一般是用户名
                .setIssuedAt(new Date(System.currentTimeMillis())) // token 创建时间
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // 设置过期时间 (10小时)
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY) // 使用 HS256 算法签名并加入密钥
                .compact();
    }

    // 验证 token 是否过期
    public Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    // 从 token 中提取用户名
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    // 提取 token 中的过期时间
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    // 提取任意声明 (自定义数据)
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    // 解析 token
    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();
    }
}

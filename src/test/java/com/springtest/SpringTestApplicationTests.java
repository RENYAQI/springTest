package com.springtest;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.springtest.entity.User;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.crypto.SecretKey;
import java.util.Base64;

@SpringBootTest
class SpringTestApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    void KeyGenerator () {
        // 生成合法的 Base64Url 编码密钥
        SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
        System.out.println("Generated Key: " + Keys.secretKeyFor(SignatureAlgorithm.HS256));// 使用这个生成的密钥
    }



}

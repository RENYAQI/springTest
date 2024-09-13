package com.springtest.controller;

import com.springtest.entity.User;
import com.springtest.service.UserService;
import com.springtest.util.ApiResponse;
import com.springtest.util.JwtTokenUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 用户信息表 前端控制器
 * </p>
 *
 * @author sara
 * @since 2021-08-12
 */
@RestController
@RequestMapping("/vue-element-admin/user")
public class UserController {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ApiResponse<Map<String, Object>> login(@RequestBody User user) {

        User loginUser = userService.login(user.getUsername(), user.getPassword());
        String token = jwtTokenUtil.generateToken(loginUser.getUsername());
        Map<String, Object> tokenMap = new HashMap<>();
        tokenMap.put("token", token);
        return ApiResponse.success(tokenMap);
    }

    @GetMapping("/info")
    public ApiResponse getInfo(@RequestHeader("Authorization") String token) {

            User userInfo = userService.getUserInfoByToken(token);
            return ApiResponse.success(userInfo);

    }

    @PostMapping("/logout")
    public ApiResponse logout() {

        return ApiResponse.success("Logout successful");
    }

    @PostMapping("/register")
    public ApiResponse<Map<String, Object>> register(@RequestBody User user) {
        userService.registerUser(user);
        String token = jwtTokenUtil.generateToken(user.getUsername());
        Map<String, Object> tokenMap = new HashMap<>();
        tokenMap.put("token", token);
        tokenMap.put("user", user);
        return ApiResponse.success(tokenMap);
    }
}


package com.springtest.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.springtest.dao.UserMapper;
import com.springtest.entity.User;
import com.springtest.service.UserService;
import com.springtest.util.JwtTokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * <p>
 * 用户信息表 服务实现类
 *
 * @author sara
 * </p>
 * @since 2021-08-12
 */
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;


    @Override
    public User getUserInfoByToken(String token) {
        User userInfo = new User();
        String tokenUserName = jwtTokenUtil.extractUsername(token);

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", tokenUserName);
        User user = getBaseMapper().selectOne(queryWrapper);
        System.out.println(user.getRoles());


        userInfo.setRoles(user.getRoles());
        userInfo.setUsername(user.getUsername());
        userInfo.setAvatar(user.getAvatar());
        userInfo.setIntroduction(user.getIntroduction());

        return userInfo;
    }



    @Override
    public void registerUser(User user) {

        User user1 = userMapper.selectByUsername(user.getUsername());
        if (user1 != null) {
            throw new RuntimeException("User already exists");
        }



        // Create a new user
        User newUser = new User();
        newUser.setUsername(user.getUsername());
        newUser.setPassword(user.getPassword());
        newUser.setRoles("admin,editor");// In a real application, hash the password before saving

        // Save user to the database
        userMapper.insert(newUser);
    }

    @Override
    public User login(String username, String password) {
        User user = userMapper.selectByUsername(username);
        if (user == null) {
            throw new RuntimeException("用户未注册");
        }

        if (!user.getPassword().equals(password)) {
            throw new RuntimeException("密码错误");
        }
        return user;
    }
}

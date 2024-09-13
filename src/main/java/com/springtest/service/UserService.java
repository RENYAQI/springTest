package com.springtest.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.springtest.entity.User;

/**
 * <p>
 * 用户信息表 服务类
 * </p>
 *
 * @author sara
 * @since 2021-08-12
 */
public interface UserService extends IService<User> {

    User getUserInfoByToken(String token);

    void registerUser(User user);

    User login(String username, String password);
}

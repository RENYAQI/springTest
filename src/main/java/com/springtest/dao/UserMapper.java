package com.springtest.dao;

import com.springtest.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 用户信息表 Mapper 接口
 * </p>
 *
 * @author sara
 * @since 2021-08-12
 */
public interface UserMapper extends BaseMapper<User> {

    User selectByUsername(@Param("username") String username);
}

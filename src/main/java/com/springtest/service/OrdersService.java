package com.springtest.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.springtest.entity.Orders;

import java.util.List;

/**
* @author 11579
* @description 针对表【orders】的数据库操作Service
* @createDate 2024-09-24 21:56:27
*/
public interface OrdersService extends IService<Orders> {

    List<Orders> getList();
}

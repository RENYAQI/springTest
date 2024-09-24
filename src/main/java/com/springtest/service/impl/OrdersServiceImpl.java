package com.springtest.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;


import com.springtest.dao.OrdersMapper;
import com.springtest.entity.Orders;
import com.springtest.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author 11579
* @description 针对表【orders】的数据库操作Service实现
* @createDate 2024-09-24 21:56:27
*/
@Service
public class OrdersServiceImpl extends ServiceImpl<OrdersMapper, Orders> implements OrdersService {

    @Autowired
    private OrdersMapper ordersMapper;

    @Override
    public List<Orders> getList() {

        Page<Orders> ordersPage = new Page<>(1,8);

        Page<Orders> page = ordersMapper.selectPage(ordersPage, null);


        return page.getRecords();
    }
}





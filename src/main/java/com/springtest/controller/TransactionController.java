package com.springtest.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.springtest.entity.Orders;
import com.springtest.service.OrdersService;
import com.springtest.util.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 业务表 前端控制器
 * </p>
 *
 * @author renyaqi
 * @since 2021-08-12
 */
@RestController
@RequestMapping("/vue-element-admin/transaction")
public class TransactionController {

    @Autowired
    private OrdersService ordersService;

    @GetMapping("/list")
    public ApiResponse<List<Orders>> getList() {

        List<Orders> getList = ordersService.getList();
            return ApiResponse.success(getList);

    }


}


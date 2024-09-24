package com.springtest.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 * &#064;TableName  orders
 */
@TableName(value ="orders")
@Data
@EqualsAndHashCode(callSuper = false)
public class Orders implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer orderId;

    /**
     * 
     */
    private String orderNo;

    /**
     * 
     */
    private Integer customerId;

    /**
     * 
     */
    private Date orderDate;

    /**
     * 
     */
    private String shippingAddress;

    /**
     * 
     */
    private BigDecimal price;

    /**
     * 
     */
    private String status;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 最终修改时间
     */
    private Date updateTime;

    /**
     * 最终修改人
     */
    private String updateBy;

    /**
     * 是否启用[0:未删除，1:已删除]
     */
    private Integer deleted;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;


}
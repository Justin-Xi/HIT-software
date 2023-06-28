package com.example.hitsoftware.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("order")
public class Order {

    @TableId
    private int orderId;
    private String orderTime;
    private String productType;
    private int productNumber;
    private float price;
    private String depository;
    private String address;
    private String customerName;
    private String contact;
    private String courierName;
}

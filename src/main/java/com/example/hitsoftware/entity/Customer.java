package com.example.hitsoftware.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("customer")
public class Customer {

    @TableId
    private String customerName;
    private String keyWord;
    private String customerContact;
    private String customerAddress;
}

package com.example.hitsoftware.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("courier")
public class Courier {

    @TableId
    private String courierName;
    private String keyWord;
    private String courierContact;
    private String free;
}

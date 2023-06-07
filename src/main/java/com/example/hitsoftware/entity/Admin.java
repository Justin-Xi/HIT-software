package com.example.hitsoftware.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("admin")
public class Admin {

    @TableId
    private String adminName;
    private String keyWord;

    public Admin(String adminName, String keyWord) {
        this.adminName = adminName;
        this.keyWord = keyWord;
    }
}

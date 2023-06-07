package com.example.hitsoftware.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("admin")
public class Admin {

    @TableId
    private String userName;
    private String keyWord;
}

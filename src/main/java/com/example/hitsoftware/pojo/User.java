package com.example.hitsoftware.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("users")
public class User {

    @TableId
    private String userName;
    private String keyWord;
    private String addRight;
    private String deleteRight;
    private String listRight;
}

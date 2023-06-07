package com.example.hitsoftware.vo;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class UserVo {

    @TableId
    private String userName;
    private String addRight;
    private String deleteRight;
    private String listRight;
}

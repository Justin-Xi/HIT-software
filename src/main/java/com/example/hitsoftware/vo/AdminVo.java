package com.example.hitsoftware.vo;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class AdminVo {

    @TableId
    private String userName;
    private String keyWord;
}

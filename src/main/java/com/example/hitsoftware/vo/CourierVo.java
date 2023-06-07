package com.example.hitsoftware.vo;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class CourierVo {

    @TableId
    private String courierName;
    private String courierContact;
    private String free;
}

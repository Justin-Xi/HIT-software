package com.example.hitsoftware.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.List;

/**
 * Package: com.example.hitsoftware.entity
 * Discription:
 *
 * @Author: JUSTIN
 * @Create:6/28/2023 - 9:59 AM
 * @Version:
 */
@Data
@TableName("warehouse")
public class WareHouse {
    @TableId
    private int wareHouseID;
    private String wareHouseAddress;
    private Manager manager;
    private List<Product> products;

    public WareHouse(int wareHouseID, String wareHouseAddress, Manager manager, List<Product> products) {
        this.wareHouseID = wareHouseID;
        this.wareHouseAddress = wareHouseAddress;
        this.manager = manager;
        this.products = products;
    }
}

package com.example.hitsoftware.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * Package: com.example.hitsoftware.entity
 * Discription:
 *
 * @Author: JUSTIN
 * @Create:6/28/2023 - 10:28 AM
 * @Version:
 */
@Data
@TableName("product")
public class Product {
    @TableId
    private final int productID;
    private final String productType;
    private WareHouse wareHouse;
    private float purchaseUnitPrice;
    private float wholesaleUnitPrice;
    private float retailUnitPrice;
    private int inventory;
    private final Supplier supplier;

    public Product(int productID, String productType, WareHouse wareHouse, float purchaseUnitPrice, float wholesaleUnitPrice, float retailUnitPrice, int inventory, Supplier supplier) {
        this.productID = productID;
        this.productType = productType;
        this.wareHouse=wareHouse;
        this.purchaseUnitPrice = purchaseUnitPrice;
        this.wholesaleUnitPrice = wholesaleUnitPrice;
        this.retailUnitPrice = retailUnitPrice;
        this.inventory = inventory;
        this.supplier = supplier;
    }
}

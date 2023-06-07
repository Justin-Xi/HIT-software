package com.example.hitsoftware.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("supplier")
public class Supplier {

    @TableId
    private String supplierName;
    private String keyWord;
    private String supplierContact;
    private String supplierAddress;

    public Supplier(String supplierName, String keyWord, String supplierContact, String supplierAddress) {
        this.supplierName = supplierName;
        this.keyWord = keyWord;
        this.supplierContact = supplierContact;
        this.supplierAddress = supplierAddress;
    }
}

package com.example.hitsoftware.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("customer")
public class Customer {

    @TableId
    private String customerName;
    private String keyWord;
    private String customerContact;
    private String customerAddress;

    public Customer(String customerName, String keyWord, String customerContact, String customerAddress) {
        this.customerName = customerName;
        this.keyWord = keyWord;
        this.customerContact = customerContact;
        this.customerAddress = customerAddress;
    }
    @Override
    public boolean equals(Object o){
        if(o==null||o.getClass()!=Customer.class){
            return false;
        }
        Customer customer=(Customer) o;
        return customer.getCustomerName().equals(this.customerName);
    }
    @Override
    public int hashCode(){
        return Integer.parseInt(this.customerName);
    }
}

package com.example.hitsoftware.controllers;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.hitsoftware.entity.Customer;
import com.example.hitsoftware.mapper.CustomerMapper;
import com.example.hitsoftware.service.*;
import com.example.hitsoftware.vo.Result;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/customer/customerInfo")
public class CustomerInfoController {
    @Autowired
    private CustomerMapper customerMapper;
    @Autowired
    private ICustomerInfoService customerInfoService;
    private Gson gson=new Gson();
    /**
     * 根据姓名查询顾客信息
     * 将数据库中的信息转成Json返回给前端
     */
    @GetMapping("/search")
    public String searchCustomer(String name){
        Customer customer=new Customer(name,null,null,null);
        for(Customer c:customerMapper.selectList(null)){
            if(c.equals(customer)){
                return gson.toJson(c);
            }
        }
        return "Customer do not exist!";
    }
    /**
     * 增加顾客
     */
    @PostMapping("/add")
    public void addCustomer(@RequestBody Customer customer){
        customerMapper.insert(customer);
    }
    /**
     * 删除顾客
     */
    @PostMapping("/remove")
    public void removeCustomer(@RequestBody Customer customer){
        customerMapper.deleteById(customer);
    }

    /**
     * 更新顾客信息
     * @param customer 新的顾客信息
     */
    @PostMapping("/update")
    public void updateCustomer(@RequestBody Customer customer){
        customerMapper.updateById(customer);
    }

}

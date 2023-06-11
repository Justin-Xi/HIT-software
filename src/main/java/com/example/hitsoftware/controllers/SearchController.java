package com.example.hitsoftware.controllers;

import com.example.hitsoftware.entity.*;
import com.example.hitsoftware.service.*;
import com.example.hitsoftware.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 查询信息的接口通用类，内部有很多用来查询信息的类
 * 目前为止，每一张表都有一个对应的查询信息的接口
 * 看好接口对应的url进行调用即可
 */
@RestController
@Slf4j
@RequestMapping("/search")
public class SearchController {

    @Autowired
    IAdminService adminService;
    @Autowired
    ICourierService courierService;
    @Autowired
    ICustomerService customerService;
    @Autowired
    IManagerService managerService;
    @Autowired
    ISupplierService supplierService;
    @Autowired
    IUserService userService;

    /**
     * 获取某一管理员信息
     * @param userName 管理员名字
     * @return json结果
     */
    @GetMapping("/admin/{userName}")
    public Result searchAdmin(@PathVariable String userName){
        log.info("admin search, userName={}",userName);
        Admin admin = adminService.getById(userName);
        if(null==admin)
            return Result.fail("Administrator does not exist");
        return Result.success(admin);
    }


    /**
     * 获取某一快递员信息
     * @param userName 快递员名字
     * @return json结果
     */
    @GetMapping("/courier/{userName}")
    public Result searchCourier(@PathVariable String userName){
        log.info("courier search, userName={}",userName);
        Courier courier = courierService.getById(userName);
        if(null==courier)
            return Result.fail("Courier does not exist");
        return Result.success(courier);
    }

    /**
     * 获取某一客户信息
     * @param userName 客户名字
     * @return json结果
     */
    @GetMapping("/customer/{userName}")
    public Result searchCustomer(@PathVariable String userName){
        log.info("customer search, userName={}",userName);
        Customer customer = customerService.getById(userName);
        if(null==customer)
            return Result.fail("Customer does not exist");
        return Result.success(customer);
    }

    /**
     * 获取某一仓库管理员信息
     * @param userName 仓库管理员名字
     * @return json结果
     */
    @GetMapping("/manager/{userName}")
    public Result searchManager(@PathVariable String userName){
        log.info("manager search, userName={}",userName);
        Manager manager = managerService.getById(userName);
        if(null==manager)
            return Result.fail("Manager does not exist");
        return Result.success(manager);
    }

    /**
     * 获取某一供应商信息
     * @param userName 供应商名字
     * @return json结果
     */
    @GetMapping("/supplier/{userName}")
    public Result searchSupplier(@PathVariable String userName){
        log.info("supplier search, userName={}",userName);
        Supplier supplier = supplierService.getById(userName);
        if(null==supplier)
            return Result.fail("Supplier does not exist");
        return Result.success(supplier);
    }

    /**
     * 获取某一用户名字
     * @param userName 用户名字
     * @return json结果
     */
    @GetMapping("/user/{userName}")
    public Result searchUser(@PathVariable String userName){
        log.info("user search, userName={}",userName);
        User user = userService.getById(userName);
        if(null==user)
            return Result.fail("User does not exist");
        return Result.success(user);
    }
}

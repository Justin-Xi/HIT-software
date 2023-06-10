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
    ICustomerInfoService customerService;
    @Autowired
    IManagerService managerService;
    @Autowired
    ISupplierInfoService supplierService;
    @Autowired
    IUserService userService;

    @GetMapping("/admin/{userName}")
    public Result searchAdmin(@PathVariable String userName){
        log.info("admin detail, userName={}",userName);
        Admin admin = adminService.getById(userName);
        if(null==admin)
            return Result.fail("Administrator does not exist");
        return Result.success(admin);
    }

    @GetMapping("/courier/{userName}")
    public Result searchCourier(@PathVariable String userName){
        log.info("courier detail, userName={}",userName);
        Courier courier = courierService.getById(userName);
        if(null==courier)
            return Result.fail("Courier does not exist");
        return Result.success(courier);
    }

    @GetMapping("/customer/{userName}")
    public Result searchCustomer(@PathVariable String userName){
        log.info("customer detail, userName={}",userName);
        Customer customer = customerService.getById(userName);
        if(null==customer)
            return Result.fail("Customer does not exist");
        return Result.success(customer);
    }

    @GetMapping("/manager/{userName}")
    public Result searchManager(@PathVariable String userName){
        log.info("manager detail, userName={}",userName);
        Manager manager = managerService.getById(userName);
        if(null==manager)
            return Result.fail("Manager does not exist");
        return Result.success(manager);
    }

    @GetMapping("/supplier/{userName}")
    public Result searchSupplier(@PathVariable String userName){
        log.info("supplier detail, userName={}",userName);
        Supplier supplier = supplierService.getById(userName);
        if(null==supplier)
            return Result.fail("Supplier does not exist");
        return Result.success(supplier);
    }

    @GetMapping("/user/{userName}")
    public Result searchUser(@PathVariable String userName){
        log.info("user detail, userName={}",userName);
        User user = userService.getById(userName);
        if(null==user)
            return Result.fail("User does not exist");
        return Result.success(user);
    }
}

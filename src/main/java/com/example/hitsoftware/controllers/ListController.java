package com.example.hitsoftware.controllers;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.hitsoftware.entity.*;
import com.example.hitsoftware.service.*;
import com.example.hitsoftware.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 */
@RestController
@Slf4j
@RequestMapping("/list")
public class ListController {

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


    /**
     * 获取所有用户列表
     * @param pageNum 第几页页码
     * @param pageSize 每一页有多少用户
     * @return 用户列表
     */
    @GetMapping("/adminList")
    public Result adminList(@RequestParam(defaultValue = "1") int pageNum, @RequestParam(defaultValue = "30")int pageSize){
        log.info("admin list, pageNum={} pageSize={}",pageNum,pageSize);
        Page<Admin> page = new Page<>(pageNum,pageSize);
        IPage<Admin> iPage = adminService.page(page);
        return Result.success(iPage);
    }
    @GetMapping("/courierList")
    public Result courierList(@RequestParam(defaultValue = "1") int pageNum, @RequestParam(defaultValue = "30")int pageSize){
        log.info("courier list, pageNum={} pageSize={}",pageNum,pageSize);
        Page<Courier> page = new Page<>(pageNum,pageSize);
        IPage<Courier> iPage = courierService.page(page);
        return Result.success(iPage);
    }
    @GetMapping("/customerList")
    public Result customerList(@RequestParam(defaultValue = "1") int pageNum, @RequestParam(defaultValue = "30")int pageSize){
        log.info("customer list, pageNum={} pageSize={}",pageNum,pageSize);
        Page<Customer> page = new Page<>(pageNum,pageSize);
        IPage<Customer> iPage = customerService.page(page);
        return Result.success(iPage);
    }
    @GetMapping("/managerList")
    public Result managerList(@RequestParam(defaultValue = "1") int pageNum, @RequestParam(defaultValue = "30")int pageSize){
        log.info("manager list, pageNum={} pageSize={}",pageNum,pageSize);
        Page<Manager> page = new Page<>(pageNum,pageSize);
        IPage<Manager> iPage = managerService.page(page);
        return Result.success(iPage);
    }
    @GetMapping("/supplierList")
    public Result supplierList(@RequestParam(defaultValue = "1") int pageNum, @RequestParam(defaultValue = "30")int pageSize){
        log.info("supplier list, pageNum={} pageSize={}",pageNum,pageSize);
        Page<Supplier> page = new Page<>(pageNum,pageSize);
        IPage<Supplier> iPage = supplierService.page(page);
        return Result.success(iPage);
    }
    @GetMapping("/userList")
    public Result userList(@RequestParam(defaultValue = "1") int pageNum, @RequestParam(defaultValue = "30")int pageSize){
        log.info("user list, pageNum={} pageSize={}",pageNum,pageSize);
        Page<User> page = new Page<>(pageNum,pageSize);
        IPage<User> iPage = userService.page(page);
        return Result.success(iPage);
    }
}

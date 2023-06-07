package com.example.hitsoftware.controllers;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.hitsoftware.entity.*;
import com.example.hitsoftware.service.*;
import com.example.hitsoftware.vo.Result;
import com.example.hitsoftware.vo.AdminVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@Slf4j
@RequestMapping("/user")
public class AdminController {

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
     * 获取用户列表
     * @param pageNum 第几页页码
     * @param pageSize 每一页有多少用户
     * @return 用户列表
     */
    @GetMapping("/admin/adminList")
    public Result adminList(@RequestParam(defaultValue = "1") int pageNum, @RequestParam(defaultValue = "30")int pageSize){
        log.info("admin list, pageNum={} pageSize={}",pageNum,pageSize);
        Page<Admin> page = new Page<>(pageNum,pageSize);
        IPage<Admin> iPage = adminService.page(page);
        return Result.success(iPage);
    }
    @GetMapping("/admin/courierList")
    public Result courierList(@RequestParam(defaultValue = "1") int pageNum, @RequestParam(defaultValue = "30")int pageSize){
        log.info("courier list, pageNum={} pageSize={}",pageNum,pageSize);
        Page<Courier> page = new Page<>(pageNum,pageSize);
        IPage<Courier> iPage = courierService.page(page);
        return Result.success(iPage);
    }
    @GetMapping("/admin/customerList")
    public Result customerList(@RequestParam(defaultValue = "1") int pageNum, @RequestParam(defaultValue = "30")int pageSize){
        log.info("customer list, pageNum={} pageSize={}",pageNum,pageSize);
        Page<Customer> page = new Page<>(pageNum,pageSize);
        IPage<Customer> iPage = customerService.page(page);
        return Result.success(iPage);
    }
    @GetMapping("/admin/managerList")
    public Result managerList(@RequestParam(defaultValue = "1") int pageNum, @RequestParam(defaultValue = "30")int pageSize){
        log.info("manager list, pageNum={} pageSize={}",pageNum,pageSize);
        Page<Manager> page = new Page<>(pageNum,pageSize);
        IPage<Manager> iPage = managerService.page(page);
        return Result.success(iPage);
    }
    @GetMapping("/admin/supplierList")
    public Result supplierList(@RequestParam(defaultValue = "1") int pageNum, @RequestParam(defaultValue = "30")int pageSize){
        log.info("supplier list, pageNum={} pageSize={}",pageNum,pageSize);
        Page<Supplier> page = new Page<>(pageNum,pageSize);
        IPage<Supplier> iPage = supplierService.page(page);
        return Result.success(iPage);
    }


    /**
     * 用于获取每一个用户的详细信息
     * @param userName 用户名
     * @return 一个用户的详细信息
     */
    @GetMapping("/admin/detail/{userName}")
    public Result adminDetail(@PathVariable String userName){
        log.info("user detail, userName={}",userName);
        Admin user = adminService.getById(userName);
        return Result.success(user);
    }


}

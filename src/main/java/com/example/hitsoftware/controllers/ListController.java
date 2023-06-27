package com.example.hitsoftware.controllers;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.hitsoftware.entity.*;
import com.example.hitsoftware.mapper.UserMapper;
import com.example.hitsoftware.service.*;
import com.example.hitsoftware.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * 用于获取列表的接口集合类，里面包含着各种获得列表的接口
 * 每张表对应着一个接口。
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
    ICustomerService customerService;
    @Autowired
    IManagerService managerService;
    @Autowired
    ISupplierService supplierService;
    @Autowired
    IUserService userService;
    @Autowired
    UserMapper userMapper;


    /**
     * 获取管理员列表
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

    /**
     * 获取管理员列表
     * @param pageNum 第几页页码
     * @param pageSize 每一页有多少用户
     * @return 用户列表
     */
    @GetMapping("/adminList2")
    public Result adminList2(@RequestParam(defaultValue = "1") int pageNum, @RequestParam(defaultValue = "30")int pageSize){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("*").eq("user_character","Admin");
        Page<User> page = new Page<>(pageNum, pageSize);
        IPage<User> iPage = userMapper.selectPage(page, queryWrapper);
        return Result.success(iPage);
    }

    /**
     * 获取快递员列表
     * @param pageNum 第几页页码
     * @param pageSize 每一页有多少用户
     * @return 用户列表
     */
    @GetMapping("/courierList")
    public Result courierList(@RequestParam(defaultValue = "1") int pageNum, @RequestParam(defaultValue = "30")int pageSize){
        log.info("courier list, pageNum={} pageSize={}",pageNum,pageSize);
        Page<Courier> page = new Page<>(pageNum,pageSize);
        IPage<Courier> iPage = courierService.page(page);
        return Result.success(iPage);
    }

    /**
     * 获取快递员列表
     * @param pageNum 第几页页码
     * @param pageSize 每一页有多少用户
     * @return 用户列表
     */
    @GetMapping("/courierList2")
    public Result courierList2(@RequestParam(defaultValue = "1") int pageNum, @RequestParam(defaultValue = "30")int pageSize){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("*").eq("user_character","Courier");
        Page<User> page = new Page<>(pageNum, pageSize);
        IPage<User> iPage = userMapper.selectPage(page, queryWrapper);
        return Result.success(iPage);
    }

    /**
     * 获取客户列表
     * @param pageNum 第几页页码
     * @param pageSize 每一页有多少用户
     * @return 用户列表
     */
    @GetMapping("/customerList")
    public Result customerList(@RequestParam(defaultValue = "1") int pageNum, @RequestParam(defaultValue = "30")int pageSize){
        log.info("customer list, pageNum={} pageSize={}",pageNum,pageSize);
        Page<Customer> page = new Page<>(pageNum,pageSize);
        IPage<Customer> iPage = customerService.page(page);
        return Result.success(iPage);
    }

    /**
     * 获取客户列表
     * @param pageNum 第几页页码
     * @param pageSize 每一页有多少用户
     * @return 用户列表
     */
    @GetMapping("/customerList2")
    public Result customerList2(@RequestParam(defaultValue = "1") int pageNum, @RequestParam(defaultValue = "30")int pageSize){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("*").eq("user_character","Customer");
        Page<User> page = new Page<>(pageNum, pageSize);
        IPage<User> iPage = userMapper.selectPage(page, queryWrapper);
        return Result.success(iPage);
    }

    /**
     * 获取仓库管理员列表
     * @param pageNum 第几页页码
     * @param pageSize 每一页有多少用户
     * @return 用户列表
     */
    @GetMapping("/managerList")
    public Result managerList(@RequestParam(defaultValue = "1") int pageNum, @RequestParam(defaultValue = "30")int pageSize){
        log.info("manager list, pageNum={} pageSize={}",pageNum,pageSize);
        Page<Manager> page = new Page<>(pageNum,pageSize);
        IPage<Manager> iPage = managerService.page(page);
        return Result.success(iPage);
    }

    /**
     * 获取供货商列表
     * @param pageNum 第几页页码
     * @param pageSize 每一页有多少用户
     * @return 用户列表
     */
    @GetMapping("/managerList2")
    public Result managerList2(@RequestParam(defaultValue = "1") int pageNum, @RequestParam(defaultValue = "30")int pageSize){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("*").eq("user_character","Manager");
        Page<User> page = new Page<>(pageNum, pageSize);
        IPage<User> iPage = userMapper.selectPage(page, queryWrapper);
        return Result.success(iPage);
    }

    /**
     * 获取供货商列表
     * @param pageNum 第几页页码
     * @param pageSize 每一页有多少用户
     * @return 用户列表
     */
    @GetMapping("/supplierList")
    public Result supplierList(@RequestParam(defaultValue = "1") int pageNum, @RequestParam(defaultValue = "30")int pageSize){
        log.info("supplier list, pageNum={} pageSize={}",pageNum,pageSize);
        Page<Supplier> page = new Page<>(pageNum,pageSize);
        IPage<Supplier> iPage = supplierService.page(page);
        return Result.success(iPage);
    }

    /**
     * 获取供货商列表
     * @param pageNum 第几页页码
     * @param pageSize 每一页有多少用户
     * @return 用户列表
     */
    @GetMapping("/supplierList2")
    public Result supplierList2(@RequestParam(defaultValue = "1") int pageNum, @RequestParam(defaultValue = "30")int pageSize){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("*").eq("user_character","Supplier");
        Page<User> page = new Page<>(pageNum, pageSize);
        IPage<User> iPage = userMapper.selectPage(page, queryWrapper);
        return Result.success(iPage);
    }

    /**
     * 获取全体用户列表
     * @param pageNum 第几页页码
     * @param pageSize 每一页有多少用户
     * @return 用户列表
     */
    @GetMapping("/userList")
    public Result userList(@RequestParam(defaultValue = "1") int pageNum, @RequestParam(defaultValue = "30")int pageSize){
        log.info("user list, pageNum={} pageSize={}",pageNum,pageSize);
        Page<User> page = new Page<>(pageNum,pageSize);
        IPage<User> iPage = userService.page(page);
        return Result.success(iPage);
    }
}

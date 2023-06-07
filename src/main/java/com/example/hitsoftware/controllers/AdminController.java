package com.example.hitsoftware.controllers;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.hitsoftware.pojo.Admin;
import com.example.hitsoftware.service.IAdminService;
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

    /**
     * 获取用户列表
     * @param pageNum 第几页页码
     * @param pageSize 每一页有多少用户
     * @return 用户列表
     */
    @GetMapping("/admin/list")
    public Result adminList(@RequestParam(defaultValue = "1") int pageNum, @RequestParam(defaultValue = "30")int pageSize){
        log.info("user list, pageNum={} pageSize={}",pageNum,pageSize);
        Page<Admin> page = new Page<>(pageNum,pageSize);
        IPage<Admin> iPage = adminService.page(page);

        List poiVoList = iPage.getRecords().stream().map(user -> {
            AdminVo userVo = new AdminVo();
            BeanUtils.copyProperties(user,userVo);
            return userVo;
        }).collect(Collectors.toList());

        iPage.setRecords(poiVoList);
        return Result.success(iPage);
    }

    /**
     * 用于获取每一个用户的详细信息
     * @param userName 用户名
     * @return 一个用户的详细信息
     */
    @GetMapping("/detail/{userName}")
    public Result adminDetail(@PathVariable String userName){
        log.info("user detail, userName={}",userName);
        Admin user = adminService.getById(userName);
        return Result.success(user);
    }


}

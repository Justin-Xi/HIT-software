package com.example.hitsoftware.controllers;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.hitsoftware.entity.Courier;
import com.example.hitsoftware.entity.User;
import com.example.hitsoftware.service.ICourierService;
import com.example.hitsoftware.service.IUserService;
import com.example.hitsoftware.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/manager")
public class ManagerController {

    @Autowired
    ICourierService courierService;
    @Autowired
    IUserService userService;

    @GetMapping("/courierList")
    public Result courierList(@RequestParam(defaultValue = "1") int pageNum, @RequestParam(defaultValue = "30")int pageSize){
        log.info("courier list, pageNum={} pageSize={}",pageNum,pageSize);
        Page<Courier> page = new Page<>(pageNum,pageSize);
        IPage<Courier> iPage = courierService.page(page);
        return Result.success(iPage);
    }

    /**
     * 用于获取用户的详细信息
     * @param userName 用户名
     * @return 一个用户的详细信息
     */
    @GetMapping("/detail/{userName}")
    public Result detail(@PathVariable String userName){
        log.info("user detail, userName={}",userName);
        User user = userService.getById(userName);
        return Result.success(user);
    }
}

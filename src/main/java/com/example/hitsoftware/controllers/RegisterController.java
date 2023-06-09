package com.example.hitsoftware.controllers;

import com.example.hitsoftware.entity.*;
import com.example.hitsoftware.service.IUserService;
import com.example.hitsoftware.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
/**
 * 注册界面的控制端口以及接口程序
 */
@RestController
@Slf4j
@RequestMapping("register")
public class RegisterController {

    @Autowired
    IUserService userService;

    /**
     * 用户注册的端口，要求前端返回用户注册信息
     */
    @GetMapping("/register")
    public Result register(@RequestBody User user) {
        AdminController adminController = new AdminController();
        return adminController.add(user);
    }
}

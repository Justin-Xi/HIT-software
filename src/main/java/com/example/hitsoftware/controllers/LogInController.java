package com.example.hitsoftware.controllers;

import com.example.hitsoftware.entity.User;
import com.example.hitsoftware.service.IUserService;
import com.example.hitsoftware.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 登录界面的控制端口以及接口程序，负责检验用户的账户和密码
 * 需要前端传输进来用户的账户和密码，前端进行传入，后端
 * 进行判断，如果用户名不符合格式，后端返回的信息中会体现
 * 若用户名正确而用户密码错误，后端也会返回failed信息。
 */
@RestController
@Slf4j
@RequestMapping("log")
public class LogInController {

    @Autowired
    IUserService userService;

    /**
     * 用户登录的端口，要求前端返回用户名和密码，
     * 里面要求储存userName=...和keyWord=...作为
     * 后端，面对不同情况会给出不同的结果。
     * @param userName 用户名
     * @param keyWord 密码
     * @return 结果类，里面储存了登录成功或者登录失败的信息
     */
    @GetMapping("/log")
    public Result login(String userName,String keyWord){
        log.info("user detail, userName={},keyWord={}",userName,keyWord);
        Pattern pattern = Pattern.compile("[0-9a-zA-Z]+");
        Matcher matcher = pattern.matcher(userName);
        if(!matcher.matches())
            return Result.fail("Format error");
        User user1 = userService.getById(userName);
        if(user1==null)
            return Result.fail("User does not exist");
        if(!user1.getKeyWord().equals(keyWord))
            return Result.fail("Password error");
        return Result.success(user1);
    }
}
package com.example.hitsoftware.controllers;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.hitsoftware.entity.*;
import com.example.hitsoftware.service.ISupplierService;
import com.example.hitsoftware.service.IUserService;
import com.example.hitsoftware.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@Slf4j
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    ISupplierService supplierService;
    @Autowired
    IUserService userService;

    /**
     * 获取供应商列表
     * @param pageNum 页数
     * @param pageSize 每页展示数目
     * @return json结果
     */
    @GetMapping("/supplierList")
    public Result supplierList(@RequestParam(defaultValue = "1") int pageNum, @RequestParam(defaultValue = "30")int pageSize){
        log.info("supplier list, pageNum={} pageSize={}",pageNum,pageSize);
        Page<Supplier> page = new Page<>(pageNum,pageSize);
        IPage<Supplier> iPage = supplierService.page(page);
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

    /**
     * 添加用户的接口，这里采用了json数据格式进行传送
     * 数据，前端在使用的时候，定义好要用的数据，用户
     * 名，密码，身份，联系方式，地址，权限，没有的可以
     * 不加，后端会赋值为null
     * @param user 用户
     * @return json结果
     */
    @PostMapping("/add")
    public Result add(@RequestBody User user){
        log.info("supplier add, user={}",user);
        Pattern pattern = Pattern.compile("[0-9a-zA-Z]+");
        Matcher matcher1 = pattern.matcher(user.getUserName());
        Matcher matcher2 = pattern.matcher(user.getKeyWord());
        User user1 = userService.getById(user.getUserName());
        if(!(matcher1.matches()&&matcher2.matches()))
            return Result.fail("Format error");
        if(user1!=null)
            return Result.fail("User exist");
        if(!user.getUserCharacter().equals("supplier"))
           return Result.fail("Identity error");
        userService.save(user);
        supplierService.save(new Supplier(user.getUserName(), user.getKeyWord(), user.getUserContact(), user.getUserAddress()));
        return Result.success();
    }

    /**
     * 删除用户的接口，将用户从数据库中删除，用户不存在
     * 的话就会返回失败信息，存在的话就会返回成功信息，
     * 作为删除的接口，只需要提供用户名就能删除
     * @param userName 用户名
     * @return json接口
     */
    @DeleteMapping("/delete/{userName}")
    public Result delete(@PathVariable String userName){
        log.info("supplier delete userName={}",userName);
        boolean flag = userService.removeById(userName);
        supplierService.removeById(userName);
        if(flag)
            return Result.success();
        return Result.fail("Removal failed");
    }
}

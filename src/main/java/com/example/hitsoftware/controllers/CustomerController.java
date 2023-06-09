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
     * 不加，后端会赋值为null。与admin的add不同，用户
     * 的删除需要前端额外传回当前正在登录的用户名称，
     * 便于判断是否有权限去添加用户。
     * @param userName 当前用户的用户名
     * @param user 用户
     * @return json结果
     */
    @PostMapping("/add/{userName}")
    public Result add(@PathVariable String userName,@RequestBody User user){
        log.info("supplier add, user={}",user);
        Pattern pattern = Pattern.compile("[0-9a-zA-Z]+");
        Matcher matcher1 = pattern.matcher(user.getUserName());
        Matcher matcher2 = pattern.matcher(user.getKeyWord());
        User user1 = userService.getById(user.getUserName());
        User user2 = userService.getById(userName);
        //判断权限是否是true
        if(user2.getAddPermission().equals("false"))
            return Result.fail("Missing permissions");
        //判断格式是否正确
        if(!(matcher1.matches()&&matcher2.matches()))
            return Result.fail("Format error");
        //判断用户是否存在
        if(user1!=null)
            return Result.fail("User exist");
        //判断身份是否正确
        if(!user.getUserCharacter().equals("Supplier"))
           return Result.fail("Identity error");
        userService.save(user);
        supplierService.save(new Supplier(user.getUserName(), user.getKeyWord(), user.getUserContact(), user.getUserAddress()));
        return Result.success();
    }

    /**
     * 删除用户的接口，将用户从数据库中删除，用户不存在
     * 的话就会返回失败信息，存在的话就会返回成功信息，
     * 作为删除的接口，只需要提供用户名就能删除
     * @param userName 当前登录用户名
     * @param userName2 要删除用户名
     * @return json接口
     */
    @DeleteMapping("/delete/{userName}")
    public Result delete(String userName,String userName2){
        log.info("supplier delete userName={},userName2={}",userName,userName2);
        User user = userService.getById(userName);
        //判断权限是否是true
        if(user.getAddPermission().equals("false")||null==user.getAddPermission())
            return Result.fail("Missing permissions");
        boolean flag = userService.removeById(userName2);
        supplierService.removeById(userName2);
        if(flag)
            return Result.success();
        return Result.fail("Removal failed");
    }
}

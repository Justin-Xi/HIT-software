package com.example.hitsoftware.controllers;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.hitsoftware.entity.*;
import com.example.hitsoftware.mapper.UserMapper;
import com.example.hitsoftware.service.*;
import com.example.hitsoftware.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 这个类是通用接口的通用类，里面包含着很多公用的方法
 * 例如detail接口，add接口，delete接口，edit接口
 * 等等，，这些接口被集成在通用类中，方便多次调用。
 */
@RestController
@Slf4j
@RequestMapping("/common")
public class CommonController {

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
    @Autowired
    UserMapper userMapper;

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
        log.info("user add, user={}",user);
        Pattern pattern = Pattern.compile("[0-9a-zA-Z]+");
        Matcher matcher1 = pattern.matcher(user.getUserName());
        Matcher matcher2 = pattern.matcher(user.getKeyWord());
        if(!(matcher1.matches()&&matcher2.matches()))
            return Result.fail("Format error");
        User user1 = userService.getById(user.getUserName());
        if(user1!=null)
            return Result.fail("User exist");
        userService.save(user);
        switch (user.getUserCharacter()) {
            case "Admin":
                adminService.save(new Admin(user.getUserName(), user.getKeyWord()));
                break;
            case "Courier":
                courierService.save(new Courier(user.getUserName(), user.getKeyWord(), user.getUserContact(), "true"));
                break;
            case "Customer":
                customerService.save(new Customer(user.getUserName(), user.getKeyWord(), user.getUserContact(), user.getUserAddress()));
                break;
            case "Manager":
                managerService.save(new Manager(user.getUserName(), user.getKeyWord(), user.getUserContact(), user.getUserAddress()));
                break;
            case "Supplier":
                supplierService.save(new Supplier(user.getUserName(), user.getKeyWord(), user.getUserContact(), user.getUserAddress()));
                break;
            default:
                return Result.fail("Identity error");
        }
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
        log.info("user delete userName={}",userName);
        boolean flag = userService.removeById(userName);
        courierService.removeById(userName);
        customerService.removeById(userName);
        managerService.removeById(userName);
        supplierService.removeById(userName);
        if(flag)
            return Result.success();
        return Result.fail("Removal failed");
    }
    /**
     * 修改用户接口，这是一个比较通用的修改接口，什么数据都能修改，
     * 前端想要修改什么数据，就将修改后用户的信息以json形式传送给
     * 后端即可,但是有一点不能变，用户名是唯一确认的，不可以发生
     * 变化
     * @param user 用户
     * @return json结果
     */
    @PutMapping("/edit")
    public Result edit(@RequestBody User user){
        log.info("user edit, user={}",user);
        this.delete(user.getUserName());
        this.add(user);
        return Result.success();
    }

    /**
     * 这是一个比较专一的接口，用于专门负责某一字段的修改，目前来说，
     * 在数据库中，我们需要去了解每一个字段的名字，在前端传回来
     * 的数据中，要包含着用户名，和修改字段的名字，后端会帮你把
     * 权限的T/F变更
     * @param userName 用户名
     * @param field 字段名称
     * @param value 字段的值
     * @return json结果
     */
    @PutMapping("/field/{userName}")
    public Result fieldEdit(@PathVariable String userName, String field, String value){
        log.info("field edit, userName={}, field={}, value={}",userName,field,value);
        User user = userService.getById(userName);
        if(user==null)
            return Result.fail("User does not exist");
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("user_name", userName).set(field, value);
        Integer rows = userMapper.update(null, updateWrapper);
        return Result.success();
    }
}

package com.example.hitsoftware.controllers;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.hitsoftware.entity.*;
import com.example.hitsoftware.mapper.UserMapper;
import com.example.hitsoftware.service.*;
import com.example.hitsoftware.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@Slf4j
@RequestMapping("/admin")
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
    @Autowired
    UserMapper userMapper;


    /**
     * 获取用户列表
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
                courierService.save(new Courier(user.getUserName(), user.getKeyWord(), user.getUserContact(), user.getUserAddress()));
                break;
            case "Manager":
                managerService.save(new Manager(user.getUserName(), user.getKeyWord(), user.getUserContact(), user.getUserAddress()));
                break;
            case "Supplier":
                supplierService.save(new Supplier(user.getUserName(), user.getKeyWord(), user.getUserContact(), user.getUserAddress()));
                break;
            default:
                return Result.fail("Exception error");
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
     * 这是一个比较专一的接口，用于专门负责权限的修改，目前来说，
     * 在数据库中，我们需要去了解每一个权限的名字，在前端传回来
     * 的数据中，要包含着用户名，和修改权限的名字，后端会帮你把
     * 权限的T/F变更
     * @param userName 用户名
     * @param permission 权限名称
     * @return json结果
     */
    @PutMapping("/permission/{userName}")
    public Result permissionEdit(@PathVariable String userName,String permission){
        log.info("permission edit, userName={}",userName);
        User user = userService.getById(userName);
        if(user==null)
            return Result.fail("User does not exist");
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
            switch (permission) {
                case "addPermission":
                    try {
                        if (user.getAddPermission().equals("false"))
                            updateWrapper.eq("user_name", userName).set("add_permission", "true");
                        else if (user.getAddPermission().equals("true"))
                            updateWrapper.eq("user_name", userName).set("add_permission", "false");
                        else
                            updateWrapper.eq("user_name", userName).set("add_permission", "false");
                        break;
                    }catch(NullPointerException e){
                        updateWrapper.eq("user_name", userName).set("add_permission", "false");
                        Integer rows = userMapper.update(null, updateWrapper);
                        return Result.success();
                    }
                case "deletePermission":
                    try {
                        if (user.getAddPermission().equals("false"))
                            updateWrapper.eq("user_name", userName).set("delete_permission", "true");
                        else if (user.getAddPermission().equals("true"))
                            updateWrapper.eq("user_name", userName).set("delete_permission", "false");
                        else
                            updateWrapper.eq("user_name", userName).set("delete_permission", "false");
                        break;
                    }catch(NullPointerException e){
                        updateWrapper.eq("user_name", userName).set("delete_permission", "false");
                        Integer rows = userMapper.update(null, updateWrapper);
                        return Result.success();
                    }
                default:
                    return Result.fail("Permission does not exist");
            }
        Integer rows = userMapper.update(null, updateWrapper);
        return Result.success();
    }
}

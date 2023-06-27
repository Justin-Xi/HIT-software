package com.example.hitsoftware.controllers.special;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.hitsoftware.entity.*;
import com.example.hitsoftware.mapper.UserMapper;
import com.example.hitsoftware.service.*;
import com.example.hitsoftware.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 这个类是属于Admin的独有类，只有用户是Admin时，
 * 前端才应调用其中的方法，里面只存在一个方法，就是
 * 更改其他用户的权限。
 */
@RestController
@Slf4j
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    IUserService userService;
    @Autowired
    UserMapper userMapper;

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
        log.info("permission edit, userName={}, permission={}",userName,permission);
        User user = userService.getById(userName);
        if(user==null)
            return Result.fail("User does not exist");
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
            switch (permission) {
                case "addPermission":
                        if (null==user.getAddPermission()||user.getAddPermission().equals("false"))
                            updateWrapper.eq("user_name", userName).set("add_permission", "true");
                        else if (user.getAddPermission().equals("true"))
                            updateWrapper.eq("user_name", userName).set("add_permission", "false");
                        break;
                case "deletePermission":
                        if (null==user.getDeletePermission()||user.getDeletePermission().equals("false"))
                            updateWrapper.eq("user_name", userName).set("delete_permission", "true");
                        else if (user.getDeletePermission().equals("true"))
                            updateWrapper.eq("user_name", userName).set("delete_permission", "false");
                        break;
                default:
                    return Result.fail("Permission does not exist");
            }
        Integer rows = userMapper.update(null, updateWrapper);
        return Result.success();
    }
}

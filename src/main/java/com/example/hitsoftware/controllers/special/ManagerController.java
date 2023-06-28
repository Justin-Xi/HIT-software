package com.example.hitsoftware.controllers.special;

import com.example.hitsoftware.entity.Manager;
import com.example.hitsoftware.entity.User;
import com.example.hitsoftware.service.IManagerService;
import com.example.hitsoftware.service.IUserService;
import com.example.hitsoftware.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 这个类是属于Manager的独有类，只有用户是Manager时，
 * 前端才应调用其中的方法，里面存在两个方法，一个是add方法
 * 另一个是delete方法。其中的add方法和delete方法只能添加
 * 或者删除Manager的人。
 */
@RestController
@Slf4j
@RequestMapping("/manager")
public class ManagerController {

    @Autowired
    IManagerService managerService;
    @Autowired
    IUserService userService;


    /**
     * 添加用户的接口，这里采用了json数据格式进行传送
     * 数据，前端在使用的时候，定义好要用的数据，用户
     * 名，密码，身份，联系方式，地址，权限，没有的可以
     * 不加，后端会赋值为null。与admin的add不同，用户
     * 的删除需要前端额外传回当前正在登录的用户名称，
     * 便于判断是否有权限去添加用户。
     * @param user 用户
     * @param userName 当前用户的用户名
     * @return json结果
     */
    @PostMapping("/add/{userName}")
    public Result add(@PathVariable String userName,@RequestBody User user){
        log.info("manager add, userName={}, user={}",userName,user);
        Pattern pattern = Pattern.compile("[0-9a-zA-Z]+");
        Matcher matcher1 = pattern.matcher(user.getUserName());
        Matcher matcher2 = pattern.matcher(user.getKeyWord());
        User user1 = userService.getById(userName);
        User user2 = userService.getById(user.getUserName());
        //判断权限是否是true
        if(null==user1.getAddPermission()||user1.getAddPermission().equals("false"))
            return Result.fail("Missing permissions");
        //判断用户是否存在
        if(user2!=null)
            return Result.fail("User exist");
        //判断格式是否正确
        if(!(matcher1.matches()&&matcher2.matches()))
            return Result.fail("Format error");
        //判断身份是否正确
        if(!user.getUserCharacter().equals("Manager"))
            return Result.fail("Identity error");
        userService.save(user);
        managerService.save(new Manager(user.getUserName(), user.getKeyWord(), user.getUserContact(), user.getUserAddress()));
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
    @DeleteMapping("/delete")
    public Result delete(String userName,String userName2) {
        log.info("manager delete userName={},userName2={}", userName, userName2);
        User user = userService.getById(userName);
        User user2 = userService.getById(userName2);
        //判断权限是否是true
        if (null == user.getAddPermission() || user.getAddPermission().equals("false"))
            return Result.fail("Missing permissions");
        if (null == user2.getUserCharacter() || user2.getUserCharacter().equals("Manager"))
            return Result.fail("Identity error");
        boolean flag = userService.removeById(userName2);
        managerService.removeById(userName2);
        if (flag)
            return Result.success();
        return Result.fail("Removal failed");
    }
}

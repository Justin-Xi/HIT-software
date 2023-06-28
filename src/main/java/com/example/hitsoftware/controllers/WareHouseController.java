package com.example.hitsoftware.controllers;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.hitsoftware.entity.Product;
import com.example.hitsoftware.entity.WareHouse;
import com.example.hitsoftware.service.IProductService;
import com.example.hitsoftware.service.IWareHouseService;
import com.example.hitsoftware.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Package: com.example.hitsoftware.controllers
 * Discription:
 *
 * @Author: JUSTIN
 * @Create:6/28/2023 - 1:24 PM
 * @Version:
 */
@RestController
@Slf4j
@RequestMapping("/warehouse")
public class WareHouseController {
    @Autowired
    IWareHouseService wareHouseService;

    /**
     * 添加仓库的方法
     * @param wareHouse 商品
     * @return 结果类
     */
    @PostMapping("/add")
    public Result addWareHouse(@RequestBody WareHouse wareHouse){
        log.info("user add, order={}",wareHouse);
        WareHouse wareHouse1 = wareHouseService.getById(wareHouse.getWareHouseID());
        if(null!=wareHouse1)
            return Result.fail("WareHouse already exists");
        wareHouseService.save(wareHouse);
        return Result.success();
    }

    /**
     * 删除仓库
     * @param id 仓库ID
     * @return 结果类
     */
    @DeleteMapping("/delete/{id}")
    public Result deleteWareHouse(@PathVariable int id){
        log.info("user add, id={}",id);
        WareHouse wareHouse = wareHouseService.getById(id);
        if(null==wareHouse)
            return Result.fail("WareHouse do not exist");
        wareHouseService.removeById(id);
        return Result.success();
    }

    /**
     * 某一仓库具体信息
     * @param id 仓库id
     * @return 结果
     */
    @GetMapping("/detail/{id}")
    public Result detailWareHouse(@PathVariable int id){
        log.info("user add, id={}",id);
        WareHouse wareHouse = wareHouseService.getById(id);
        if(null==wareHouse)
            return Result.fail("WareHouse do not exists");
        return Result.success(wareHouse);
    }

    /**
     * 全部仓库列表
     * @param pageNum 页码
     * @param pageSize 页容量
     * @return 结果
     */
    @GetMapping("/list")
    public Result listWareHouse(@RequestParam(defaultValue = "1") int pageNum, @RequestParam(defaultValue = "30")int pageSize){
        log.info("user list, pageNum={} pageSize={}",pageNum,pageSize);
        Page<WareHouse> page = new Page<>(pageNum,pageSize);
        IPage<WareHouse> iPage = wareHouseService.page(page);
        return Result.success(iPage);
    }

    /**
     * 修改某一仓库信息
     * @param warehouse 修改后的仓库
     * @return 结果
     */
    @PutMapping("/edit")
    public Result editWareHouse(@RequestBody WareHouse wareHouse){
        log.info("user list, product={}",wareHouse);
        this.deleteWareHouse(wareHouse.getWareHouseID());
        this.addWareHouse(wareHouse);
        return Result.success();
    }
}

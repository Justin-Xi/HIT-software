package com.example.hitsoftware.controllers;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.hitsoftware.entity.Order;
import com.example.hitsoftware.service.IOrderService;
import com.example.hitsoftware.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/order")
public class OrderController {

    @Autowired
    IOrderService orderService;

    /**
     * 添加订单的方法
     * @param order 订单
     * @return 结果类
     */
    @PostMapping("/add")
    public Result addOrder(@RequestBody Order order){
        log.info("user add, order={}",order);
        Order order1 = orderService.getById(order.getOrderId());
        if(null!=order1)
            return Result.fail("Order exists");
        orderService.save(order);
        return Result.success();
    }

    /**
     * 删除订单的方法
     * @param id 订单编号
     * @return 结果类
     */
    @DeleteMapping("/delete/{id}")
    public Result deleteOrder(@PathVariable int id){
        log.info("user add, id={}",id);
        Order order = orderService.getById(id);
        if(null==order)
            return Result.fail("Order not exists");
        orderService.removeById(id);
        return Result.success();
    }

    /**
     * 某一订单详细信息
     * @param id 订单编号
     * @return 结果
     */
    @GetMapping("/detail/{id}")
    public Result detailOrder(@PathVariable int id){
        log.info("user add, id={}",id);
        Order order = orderService.getById(id);
        if(null==order)
            return Result.fail("Order not exists");
        return Result.success(order);
    }

    /**
     * 全部订单列表
     * @param pageNum 页码
     * @param pageSize 页容量
     * @return 结果
     */
    @GetMapping("/list")
    public Result listOrder(@RequestParam(defaultValue = "1") int pageNum, @RequestParam(defaultValue = "30")int pageSize){
        log.info("user list, pageNum={} pageSize={}",pageNum,pageSize);
        Page<Order> page = new Page<>(pageNum,pageSize);
        IPage<Order> iPage = orderService.page(page);
        return Result.success(iPage);
    }

    /**
     * 修改某一订单
     * @param order 修改后订单
     * @return 结果
     */
    @PutMapping("/edit")
    public Result editOrder(@RequestBody Order order){
        log.info("user list, order={}",order);
        this.deleteOrder(order.getOrderId());
        this.addOrder(order);
        return Result.success();
    }
}

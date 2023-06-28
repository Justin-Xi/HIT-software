package com.example.hitsoftware.controllers;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.hitsoftware.entity.Product;
import com.example.hitsoftware.service.IProductService;
import com.example.hitsoftware.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Package: com.example.hitsoftware.controllers
 * Discription:
 *
 * @Author: JUSTIN
 * @Create:6/28/2023 - 11:53 AM
 * @Version:
 */
@RestController
@Slf4j
@RequestMapping("/product")
public class ProductController {
    @Autowired
    IProductService productService;

    /**
     * 添加商品的方法
     * @param product 商品
     * @return 结果类
     */
    @PostMapping("/add")
    public Result addProduct(@RequestBody Product product){
        log.info("user add, order={}",product);
        Product product1 = productService.getById(product.getProductID());
        if(null!=product1)
            return Result.fail("Product already exists");
        productService.save(product);
        return Result.success();
    }

    /**
     * 删除商品
     * @param id 商品ID编号
     * @return 结果类
     */
    @DeleteMapping("/delete/{id}")
    public Result deleteProduct(@PathVariable int id){
        log.info("user add, id={}",id);
         Product product = productService.getById(id);
        if(null==product)
            return Result.fail("Product do not exist");
        productService.removeById(id);
        return Result.success();
    }

    /**
     * 某一商品详细信息
     * @param id 商品id
     * @return 结果
     */
    @GetMapping("/detail/{id}")
    public Result detailProduct(@PathVariable int id){
        log.info("user add, id={}",id);
         Product product = productService.getById(id);
        if(null==product)
            return Result.fail("Product do not exist");
        return Result.success(product);
    }

    /**
     * 全部商品列表
     * @param pageNum 页码
     * @param pageSize 页容量
     * @return 结果
     */
    @GetMapping("/list")
    public Result listProduct(@RequestParam(defaultValue = "1") int pageNum, @RequestParam(defaultValue = "30")int pageSize){
        log.info("user list, pageNum={} pageSize={}",pageNum,pageSize);
        Page<Product> page = new Page<>(pageNum,pageSize);
        IPage<Product> iPage = productService.page(page);
        return Result.success(iPage);
    }

    /**
     * 修改某一商品信息
     * @param product 修改后的商品
     * @return 结果
     */
    @PutMapping("/edit")
    public Result editOrder(@RequestBody Product product){
        log.info("user list, product={}",product);
        this.deleteProduct(product.getProductID());
        this.addProduct(product);
        return Result.success();
    }
}


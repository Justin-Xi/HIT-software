package com.example.hitsoftware.controllers;


import com.example.hitsoftware.entity.Supplier;
import com.example.hitsoftware.mapper.SupplierMapper;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/supplier/supplierInfo")
public class SupplierInfoController {
    @Autowired
    private SupplierMapper supplierMapper;
    private Gson gson=new Gson();
    /**
     * 依据姓名查询供应商信息
     * 将数据库中的信息转成Json返回给前端
     */
    @GetMapping("/search")
    public String searchSupplier(String name){
        Supplier supplier=new Supplier(name,null,null,null);
        for(Supplier s:supplierMapper.selectList(null)){
            if(s.equals(supplier)){
                return gson.toJson(s);
            }
        }
        return "Supplier do not exist!";
    }
    /**
     * 增加供应商
     */
    @PostMapping("/add")
    public void addSupplier(@RequestBody Supplier supplier){
        supplierMapper.insert(supplier);
    }
    /**
     * 删除供应商
     */
    @PostMapping("/remove")
    public void removeSupplier(@RequestBody Supplier supplier){
        supplierMapper.deleteById(supplier);
    }

    /**
     * 更新供应商信息
     * @param supplier 新的供应商信息
     */
    @PostMapping("/update")
    public void updateSupplier(@RequestBody Supplier supplier){
        supplierMapper.updateById(supplier);
    }

}

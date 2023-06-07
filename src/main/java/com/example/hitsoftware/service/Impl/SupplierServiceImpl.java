package com.example.hitsoftware.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.hitsoftware.mapper.SupplierMapper;
import com.example.hitsoftware.entity.Supplier;
import com.example.hitsoftware.service.ISupplierService;
import org.springframework.stereotype.Service;

@Service
public class SupplierServiceImpl extends ServiceImpl<SupplierMapper, Supplier> implements ISupplierService {
}

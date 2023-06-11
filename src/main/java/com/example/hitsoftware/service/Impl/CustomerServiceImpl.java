package com.example.hitsoftware.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.hitsoftware.mapper.CustomerMapper;
import com.example.hitsoftware.entity.Customer;
import com.example.hitsoftware.service.ICustomerService;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl extends ServiceImpl<CustomerMapper, Customer> implements ICustomerService {
}

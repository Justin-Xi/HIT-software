package com.example.hitsoftware.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.hitsoftware.mapper.CourierMapper;
import com.example.hitsoftware.entity.Courier;
import com.example.hitsoftware.service.ICourierService;
import org.springframework.stereotype.Service;

@Service
public class CourierServiceImpl extends ServiceImpl<CourierMapper, Courier> implements ICourierService {
}

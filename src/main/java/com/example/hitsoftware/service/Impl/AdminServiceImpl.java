package com.example.hitsoftware.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.hitsoftware.mapper.AdminMapper;
import com.example.hitsoftware.entity.Admin;
import com.example.hitsoftware.service.IAdminService;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements IAdminService {
}

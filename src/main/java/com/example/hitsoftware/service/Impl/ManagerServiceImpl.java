package com.example.hitsoftware.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.hitsoftware.mapper.ManagerMapper;
import com.example.hitsoftware.entity.Manager;
import com.example.hitsoftware.service.IManagerService;
import org.springframework.stereotype.Service;

@Service
public class ManagerServiceImpl extends ServiceImpl<ManagerMapper, Manager> implements IManagerService {
}

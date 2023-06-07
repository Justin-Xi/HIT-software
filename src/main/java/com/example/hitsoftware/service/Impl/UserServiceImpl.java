package com.example.hitsoftware.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.hitsoftware.entity.User;
import com.example.hitsoftware.mapper.UserMapper;
import com.example.hitsoftware.service.IUserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
}

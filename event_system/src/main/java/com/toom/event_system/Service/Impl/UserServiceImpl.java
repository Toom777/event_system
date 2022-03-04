package com.toom.event_system.Service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.toom.event_system.Entity.User;
import com.toom.event_system.Mapper.UserMapper;
import com.toom.event_system.Service.UserService;
import org.springframework.stereotype.Service;

/**
 * @author Toom
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}

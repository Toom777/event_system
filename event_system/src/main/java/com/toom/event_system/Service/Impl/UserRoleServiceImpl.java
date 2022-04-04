package com.toom.event_system.Service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.toom.event_system.Entity.UserRole;
import com.toom.event_system.Mapper.UserRoleMapper;
import com.toom.event_system.Service.UserRoleService;
import org.springframework.stereotype.Service;

/**
 * 用户和角色关联Service业务层处理
 * @author Toom
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {

}

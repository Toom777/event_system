package com.toom.event_system.Service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.toom.event_system.Entity.Role;
import com.toom.event_system.Mapper.RoleMapper;
import com.toom.event_system.Service.RoleService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * 角色表 服务实现类
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    /**
     * 查找用户权限  后续转为查role_key
     * @param username
     * @return
     */
    @Override
    public Set<String> getRoleByUsername(String username) {
        return roleMapper.selectRoleByUsername(username);
    }
}

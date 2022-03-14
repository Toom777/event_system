package com.toom.event_system.Service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.toom.event_system.Entity.Role;

import java.util.Set;

/**
 * 角色表 服务类
 * @author Toom
 */
public interface RoleService extends IService<Role> {
    /**
     * 获取用户权限
     * @param username
     * @return
     */
    Set<String> getRoleByUsername(String username);



}

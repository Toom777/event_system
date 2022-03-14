package com.toom.event_system.Service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.toom.event_system.Entity.Menu;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 菜单表 服务类
 * @author Toom
 */
public interface MenuService extends IService<Menu> {
    /**
     * 获取用户菜单权限
     * @return
     */
    public Set<String> getPermissionByUsername(String username);

    /**
     * 获取用户所能提供的菜单
     * @param username
     * @return
     */
    public List<Map<String, Object>> getMenuList(String username);


}

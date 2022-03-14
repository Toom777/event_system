package com.toom.event_system.Service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.toom.event_system.Entity.Menu;
import com.toom.event_system.Mapper.MenuMapper;
import com.toom.event_system.Service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 菜单表 服务实现类
 * @author Toom
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {

    @Autowired
    private MenuMapper menuMapper;
    /**
     * 获取用户菜单权限
     * @param username
     * @return
     */
    @Override
    public Set<String> getPermissionByUsername(String username) {
        return menuMapper.selectPermissionByUsername(username);
    }

    /**
     * 获取用户父级菜单
     * @param username
     * @return
     */
    @Override
    public List<Map<String, Object>> getMenuList(String username) {
        List<Map<String, Object>> menus = new ArrayList<>();
        //若用户权限为ADMIN则返回全部菜单列表
        if (username == "admin"){
            //TODO
            return menus;
        }else {
            //获取父级菜单
            List<Menu> list = menuMapper.selectFatherPermissionByUsername(username);
            //遍历父级菜单
            for (Menu menu : list) {
                Map<String, Object> map = new HashMap<>();
                List<Menu> subMenu = menuMapper.selectSubPermissionByFatherId(menu.getMenuId());
                map.put("subMenu", subMenu);
                map.put("menu", menu);
                menus.add(map);
            }
            return menus;
        }
    }




}

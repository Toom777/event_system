package com.toom.event_system.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.toom.event_system.Entity.Menu;
import com.toom.event_system.Entity.Role;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

/**
 *  菜单表 Mapper 接口
 * @author Toom
 */
@Repository
public interface MenuMapper  extends BaseMapper<Menu> {

    /**
     * 获取用户菜单子权限
     * @param username
     * @return
     */
    @Select("select perms from menu m, user u, role r, role_menu rm, user_role ur where u.username = #{username} and parent_id != 0 and u.user_id = ur.user_id and r.role_id = ur.role_id and rm.role_id = r.role_id and rm.menu_id = m.menu_id ")
    Set<String> selectPermissionByUsername(String username);

    /**
     * 获取当前用户父级菜单
     * @param username
     * @return
     */
    @Select("SELECT DISTINCT m.menu_id, m.parent_id, m.menu_name, m.path, m.component, m.visible, m.`status`, IFNULL(m.perms, '') as perms, m.order_num\n" +
            "FROM menu m\n" +
            "\t\tLEFT JOIN role_menu rm on m.menu_id = rm.menu_id\n" +
            "\t\tLEFT JOIN user_role ur on rm.role_id = ur.role_id\n" +
            "\t\tLEFT JOIN role r on ur.role_id = r.role_id\n" +
            "\t\tLEFT JOIN `user` u on ur.user_id = u.user_id\n" +
            "WHERE u.username = #{username} and m.menu_type = 'M' and m.`status` = 0 and parent_id = 0\n" +
            "ORDER BY m.parent_id, order_num")
    List<Menu> selectFatherPermissionByUsername(String username);


    /**
     * 获取父级菜单下的所有子菜单
     * @param parentId
     * @return
     */
    @Select("SELECT DISTINCT m.menu_id, m.parent_id, m.menu_name, m.path, m.component, m.visible, m.`status`, IFNULL(m.perms, '') as perms, m.order_num\n" +
            "FROM menu m\n" +
            "WHERE parent_id = #{parent_id} and m.menu_type = 'C' and m.`status` = 0\n" +
            "ORDER BY m.menu_id, order_num")
    List<Menu> selectSubPermissionByFatherId(Long parentId);
}

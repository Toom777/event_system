package com.toom.event_system.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.toom.event_system.Entity.Role;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

/**
 * 角色表 Mapper 接口
 */
@Repository
public interface RoleMapper extends BaseMapper<Role> {
    /**
     * 获取用户权限
     * @param username
     * @return
     */
    @Select("select r.role_name from role r, user u, user_role ur where username = #{username} and ur.user_id = u.user_id and ur.role_id = r.role_id")
    Set<String> selectRoleByUsername(String username);

}

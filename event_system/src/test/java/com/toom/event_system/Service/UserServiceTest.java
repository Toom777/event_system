package com.toom.event_system.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.toom.event_system.Entity.User;
import com.toom.event_system.Entity.UserRole;
import com.toom.event_system.Mapper.UserMapper;
import com.toom.event_system.Mapper.UserRoleMapper;
import org.junit.jupiter.api.Test;
import org.omg.Messaging.SYNC_WITH_TRANSPORT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Autowired
    private CommunityService communityService;

    @Autowired
    private UserMapper userMapper;


    @Test
    public void userSearchTest() {
        UpdateWrapper<User> wrapper = new UpdateWrapper<>();
        wrapper.eq("community_id", 11).set("community_id", 10);

        System.out.println(userService.update(null, wrapper));

    }

    @Test
    void Test() {
        UpdateWrapper<UserRole> wrapper = new UpdateWrapper<>();
        wrapper.eq("user_id", 14L).set("role_id", 103L);
        //System.out.println(userRoleService.update(null, wrapper));

        System.out.println(userRoleMapper.update(null, wrapper));

    }
}

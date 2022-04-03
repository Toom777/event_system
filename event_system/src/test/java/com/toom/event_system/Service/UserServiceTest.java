package com.toom.event_system.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.toom.event_system.Entity.User;
import com.toom.event_system.Mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceTest {

    @Autowired
    private UserService userService;

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
}

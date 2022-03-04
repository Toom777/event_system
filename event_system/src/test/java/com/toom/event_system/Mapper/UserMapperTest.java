package com.toom.event_system.Mapper;

import com.toom.event_system.Entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    void test(){
        User user = userMapper.selectById(1);
        System.out.println(user);
    }

}

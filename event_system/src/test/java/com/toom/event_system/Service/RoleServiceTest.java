package com.toom.event_system.Service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class RoleServiceTest {

    @Autowired
    private RoleService roleService;

    @Test
    public void Test(){
        System.out.println(roleService.getRoleByUsername("admin"));
    }

}

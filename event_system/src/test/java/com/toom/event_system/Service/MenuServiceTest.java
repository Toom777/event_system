package com.toom.event_system.Service;

import com.toom.event_system.Entity.Menu;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MenuServiceTest {

    @Autowired
    private MenuService menuService;

    @Test
    public void Test(){
        System.out.println(menuService.getPermissionByUsername("admin"));
        System.out.println(menuService.getPermissionByUsername("manager2"));
        System.out.println(menuService.getPermissionByUsername("user1"));
    }

    @Test
    public void Test2(){
        System.out.println(menuService.getMenuList("manager1"));
    }
}

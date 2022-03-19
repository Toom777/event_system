package com.toom.event_system.Mapper;

import com.toom.event_system.Entity.Menu;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MenuMapperTest {

    @Autowired
    private MenuMapper menuMapper;

    @Test
    public void Test(){
        List<Menu> list = menuMapper.selectList(null);
        System.out.println(list);
    }

}

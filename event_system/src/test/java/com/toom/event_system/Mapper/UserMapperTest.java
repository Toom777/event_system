package com.toom.event_system.Mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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
        Page<User> page1 = new Page<>(1, 3);
        // 第二个参数Wrapper为null,表示没有where子句，即查询表中所有的记录
        Page<User> userPage1 = userMapper.selectPage(page1, null);
        userPage1.getRecords().forEach(System.out::println);

        Page<User> page2 = new Page<>(2, 3);
        Page<User> userPage2 = userMapper.selectPage(page2, null);
        userPage2.getRecords().forEach(System.out::println);
    }

    @Test
    void PageTest() {
        Page<User> page = new Page<>(2,3);
        userMapper.selectPage(page, null);
        //获取当前页数据
        System.out.println(page.getRecords());
        //获取当前页页码
        System.out.println("当前页页码: " + page.getCurrent());
        //获取总页数
        System.out.println("总页数: " + page.getPages());
        //获取总记录数
        System.out.println("总记录数: " + page.getTotal());
        //是否有下一页
        System.out.println("是否有下一页: " + page.hasNext());
        //是否有上一页
        System.out.println("是否有上一页: " + page.hasPrevious());
    }

    @Test
    void PageTestVo() {
        Page<User> page = new Page<>(1, 5);
        userMapper.selectUserVo(page, 1L);
        //获取当前页数据
        System.out.println(page.getRecords());
        //获取当前页页码
        System.out.println("当前页页码: " + page.getCurrent());
        //获取总页数
        System.out.println("总页数: " + page.getPages());
        //获取总记录数
        System.out.println("总记录数: " + page.getTotal());
        //是否有下一页
        System.out.println("是否有下一页: " + page.hasNext());
        //是否有上一页
        System.out.println("是否有上一页: " + page.hasPrevious());

    }
}

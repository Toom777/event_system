package com.toom.event_system.Mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.toom.event_system.Entity.Community;
import com.toom.event_system.Entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
class CommunityMapperTest {

    @Autowired
    private CommunityMapper communityMapper;

    @Test
    void test(){
/*        Community community = new Community();
        community.setCommunityName("金山社区");
        community.setCreateBy("admin");
        System.out.println(communityMapper.insert(community));*/

/*        List<Community> list = communityMapper.selectList(null);
        list.forEach(System.out::println);*/

/*        QueryWrapper<Community> wrapper = new QueryWrapper<>();
        wrapper.isNotNull("community_id");*/

        // 创建分页对象（X表示第X页；Y表示每页大小为Y）
/*        Page<Community> page = new Page<>(1, 2);
        communityMapper.selectPage(page, wrapper);
        System.out.println("size: " + page.getSize());
        System.out.println("total: " + page.getTotal());
        for(Community bean : page.getRecords()) {
            System.out.println(bean);
        }*/



        QueryWrapper<Community> wrapper = new QueryWrapper<>();
        wrapper.eq("community_name", "九龙社区");
        Community community = communityMapper.selectOne(wrapper);
        System.out.println(community.getCommunityId());
    }

    @Test
    public void IdNameTest(){

        QueryWrapper<Community> wrapper = new QueryWrapper<>();
        wrapper.select("community_id", "community_name");
        List<Map<String, Object>> communityNameList = new ArrayList<>();

        for ( Community name: communityMapper.selectList(wrapper)) {
            Map<String, Object> map = new HashMap<>();
            map.put("communityName", name.getCommunityName());
            map.put("communityId", name.getCommunityId());
            communityNameList.add(map);
        }
        System.out.println(communityNameList);
    }

}

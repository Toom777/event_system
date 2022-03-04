package com.toom.event_system.Mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.toom.event_system.Entity.Community;
import com.toom.event_system.Entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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

        QueryWrapper<Community> wrapper = new QueryWrapper<>();
        wrapper.isNotNull("community_id");

        // 创建分页对象（X表示第X页；Y表示每页大小为Y）
        Page<Community> page = new Page<>(1, 2);
        communityMapper.selectPage(page, wrapper);
        System.out.println("size: " + page.getSize());
        System.out.println("total: " + page.getTotal());
        for(Community bean : page.getRecords()) {
            System.out.println(bean);
        }
    }

}

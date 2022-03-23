package com.toom.event_system.Service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.toom.event_system.Entity.Community;
import com.toom.event_system.Mapper.CommunityMapper;
import com.toom.event_system.Service.CommunityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 社区管理方法
 * @author Toom
 */
@Service
public class CommunityServiceImpl extends ServiceImpl<CommunityMapper, Community> implements CommunityService {

    @Autowired
    private CommunityMapper communityMapper;

    /**
     * 获取所有社区
     */
    @Override
    public List<Community> selectCommunityAll() {
        return communityMapper.selectList(null);
    }

    /**
     *  通过ID查找社区
     */
    @Override
    public Community selectCommunityById(Long communityId) {
        return communityMapper.selectById(communityId);
    }

    /**
     *  通过名字查找社区ID
     */
    @Override
    public Long selectCommunityByName(String communityName) {
        QueryWrapper<Community> wrapper = new QueryWrapper<>();
        //查询community_name字段为communityName的数据
        wrapper.eq("community_name", communityName);
        return communityMapper.selectOne(wrapper).getCommunityId();
    }

    /**
     *  获取所有社区名字
     * @return
     */
    @Override
    public List<String> selectCommunityNameAll() {
        QueryWrapper<Community> wrapper = new QueryWrapper<>();
        wrapper.select("community_name");
        List<String> communityNameList = new ArrayList<>();
        for ( Community name: communityMapper.selectList(wrapper)) {
            communityNameList.add(name.getCommunityName());
        }
        return communityNameList;
    }

    /**
     * 获取所有社区id和名称
     * @return
     */
    @Override
    public List<Map<String, Object>> selectCommunityIdAndNameAll() {
        QueryWrapper<Community> wrapper = new QueryWrapper<>();
        wrapper.select("community_id", "community_name");
        List<Map<String, Object>> communityNameList = new ArrayList<>();
        for ( Community name: communityMapper.selectList(wrapper)) {
            Map<String, Object> map = new HashMap<>();
            map.put("communityName", name.getCommunityName());
            map.put("communityId", name.getCommunityId());
            communityNameList.add(map);
        }
        return communityNameList;
    }

    /**
     * 新增社区
     * @param community
     * @return
     */
    @Override
    public Boolean insertCommunity(Community community) {
        int row = communityMapper.insert(community);
        return row > 0 ? true : false;
    }

    /**
     * 修改社区信息
     */
    @Override
    public Boolean updateCommunity(Community community) {
        int row = communityMapper.updateById(community);
        return row > 0 ? true : false;
    }

    /**
     * 删除社区
     */
    @Override
    public Boolean deleteCommunityById(Long communityId) {
        int row = communityMapper.deleteById(communityId);
        return row > 0 ? true : false;
    }

    /**
     * 批量删除社区
     * @param communityIds
     * @return
     */
    @Override
    public Boolean deleteCommunityByIds(Long[] communityIds) {
        List<Long> list = new ArrayList<>();
        for (long id : communityIds) {
            list.add(id);
        }
        return communityMapper.deleteBatchIds(list) > 0 ? true : false;
    }

    /**
     * 分页查询
     * @param page
     * @param wrapper
     * @return
     */
    @Override
    public Page<Community> searchCommunityPage(Page<Community> page, QueryWrapper<Community> wrapper) {
        return communityMapper.selectPage(page, wrapper);
    }
}

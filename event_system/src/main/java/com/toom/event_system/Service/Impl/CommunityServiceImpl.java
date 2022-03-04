package com.toom.event_system.Service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.toom.event_system.Entity.Community;
import com.toom.event_system.Mapper.CommunityMapper;
import com.toom.event_system.Service.CommunityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
}
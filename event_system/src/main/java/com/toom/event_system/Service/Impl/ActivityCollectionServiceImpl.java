package com.toom.event_system.Service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.toom.event_system.Entity.ActivityCollection;
import com.toom.event_system.Entity.ActivityCollection;
import com.toom.event_system.Entity.User;
import com.toom.event_system.Mapper.ActivityCollectionMapper;
import com.toom.event_system.Service.ActivityCollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ActivityCollectionServiceImpl extends ServiceImpl<ActivityCollectionMapper, ActivityCollection> implements ActivityCollectionService {

    @Autowired
    private ActivityCollectionMapper activityCollectionMapper;

    @Override
    public ActivityCollection selectActivityCollectionById(Long activityCollectionId) {
        return activityCollectionMapper.selectById(activityCollectionId);
    }

    /**
     * 新增活动收藏
     * @param activityCollection
     * @return
     */
    @Override
    public Boolean insertActivityCollection(ActivityCollection activityCollection) {
        int row = activityCollectionMapper.insert(activityCollection);
        return row > 0 ? true : false;
    }

    /**
     * 修改活动收藏信息
     */
    @Override
    public Boolean updateActivityCollection(ActivityCollection activityCollection) {
        activityCollection.setUpdateTime(new Date());
        return activityCollectionMapper.updateById(activityCollection) > 0 ? true : false;
    }

    /**
     * 删除活动收藏
     */
    @Override
    public Boolean deleteActivityCollectionById(Long activityCollectionId) {
        return activityCollectionMapper.deleteById(activityCollectionId) > 0 ? true : false;
    }

    /**
     * 批量删除活动收藏
     * @param activityCollectionIds
     * @return
     */
    @Override
    public Boolean deleteActivityCollectionByIds(Long[] activityCollectionIds) {
        List<Long> list = new ArrayList<>();
        for (long id : activityCollectionIds) {
            list.add(id);
        }
        return activityCollectionMapper.deleteBatchIds(list) > 0 ? true : false;
    }

    /**
     * 分页查询
     * @param page
     * @param wrapper
     * @return
     */
    @Override
    public Page<ActivityCollection> searchActivityCollectionPage(Page<ActivityCollection> page, QueryWrapper<ActivityCollection> wrapper) {
        return activityCollectionMapper.selectPage(page, wrapper);
    }
}

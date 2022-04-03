package com.toom.event_system.Service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.toom.event_system.Entity.Activity;
import com.toom.event_system.Mapper.ActivityMapper;
import com.toom.event_system.Service.ActivityService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 活动信息表 服务实现类
 * @author Toom
 */
@Service
public class ActivityServiceImpl extends ServiceImpl<ActivityMapper, Activity> implements ActivityService {

    @Autowired
    private ActivityMapper activityMapper;

    /**
     * 新增活动
     * @param activity 活动信息
     * @return
     */
    @Override
    public Boolean insertActivity(Activity activity) {
        return activityMapper.insert(activity) > 0 ? true : false;
    }

    /**
     * 修改活动
     * @param activity 活动信息
     * @return
     */
    @Override
    public Boolean updateActivity(Activity activity) {
        activity.setUpdateTime(new Date());
        return activityMapper.updateById(activity) > 0 ? true : false;
    }

    /**
     * 批量删除活动
     * @param activityIds 需要删除的活动信息主键集合
     * @return
     */
    @Override
    public Boolean deleteActivityByActivityIds(Long[] activityIds) {
        List<Long> list = new ArrayList<>();
        for (long id : activityIds) {
            list.add(id);
        }
        return activityMapper.deleteBatchIds(list) > 0 ? true : false;
    }


    /**
     * 删除单个活动
     * @param activityId 活动信息主键
     * @return
     */
    @Override
    public Boolean deleteActivityByActivityId(Long activityId) {
        return activityMapper.deleteById(activityId) > 0 ? true : false;
    }


    /**
     * 统计活动项目数量
     * @return
     */
    @Override
    public Long countActivity() {
        return activityMapper.selectCount(null);
    }

    /**
     * 分页查询
     * @param page
     * @param wrapper
     * @return
     */
    @Override
    public Page<Activity> searchActivityPage(Page<Activity> page, QueryWrapper<Activity> wrapper) {
        return activityMapper.selectPage(page, wrapper);
    }

    /**
     * 通过ID查活动
     * @param activityId
     * @return
     */
    @Override
    public Activity selectUserById(Long activityId) {
        return activityMapper.selectById(activityId);
    }

    /**
     * 判断状态变化
     * @param activity
     * @return
     */
    @Override
    public Activity statusChange(Activity activity) {

        Date beginTime = activity.getBeginTime();
        Date endTime = activity.getEndTime();
        Date nowTime = new Date();

        if (beginTime.after(nowTime)){
            //活动未开始
            activity.setStatus("0");
        }else if (beginTime.before(nowTime) && endTime.after(nowTime)){
            //活动进行时
            activity.setStatus("1");
        }else if (endTime.before(nowTime)){
            //已经结束辣
            activity.setStatus("2");
        }
        activityMapper.updateById(activity);
        return activity;
    }

    /**
     * 获取活动标签
     * @return
     */
    @Override
    public List<Map<String, Object>> selectActivityTag() {
        QueryWrapper<Activity> wrapper = new QueryWrapper<>();
        wrapper.select("DISTINCT activity_tag");
        List<Map<String, Object>> tagList = new ArrayList<>();
        for (Activity tag : activityMapper.selectList(wrapper)) {
            Map<String, Object> map = new HashMap<>();
            map.put("tagNum", tag.getActivityTag());
            map.put("tagName", tag.getActivityTag());
            tagList.add(map);
        }
        return tagList;
    }
}

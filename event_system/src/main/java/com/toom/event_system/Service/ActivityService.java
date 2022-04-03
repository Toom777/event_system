package com.toom.event_system.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.toom.event_system.Entity.Activity;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * 活动信息表 服务类
 * @author Toom
 */
public interface ActivityService extends IService<Activity> {

    /**
     * 新增活动信息
     *
     * @param activity 活动信息
     * @return 结果
     */
    public Boolean insertActivity(Activity activity);

    /**
     * 修改活动信息
     *
     * @param activity 活动信息
     * @return 结果
     */
    public Boolean updateActivity(Activity activity);

    /**
     * 批量删除活动信息
     *
     * @param activityIds 需要删除的活动信息主键集合
     * @return 结果
     */
    public Boolean deleteActivityByActivityIds(Long[] activityIds);

    /**
     * 删除活动信息信息
     *
     * @param activityId 活动信息主键
     * @return 结果
     */
    public Boolean deleteActivityByActivityId(Long activityId);

    /**
     * 统计活动项目数量
     * @return
     */
    public Long countActivity();


    /***
     * 分页查询
     * @param page
     * @param wrapper
     * @return
     */
    public Page<Activity> searchActivityPage(Page<Activity> page, QueryWrapper<Activity> wrapper);


    /**
     * 通过Id查活动
     * @param activityId
     * @return
     */
    public Activity selectUserById(Long activityId);

    /**
     * 活动状态变化
     * @param activity
     * @return
     */
    public Activity statusChange(Activity activity);

    /**
     * 获取活动标签
     * @return
     */
    public List<Map<String, Object>> selectActivityTag();
}

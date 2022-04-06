package com.toom.event_system.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.toom.event_system.Entity.ActivityCollection;
import com.toom.event_system.Entity.ActivityCollection;

/**
 * 活动收藏表
 * @author Toom
 */
public interface ActivityCollectionService  extends IService<ActivityCollection> {

    /**
     *  通过ID查找活动收藏
     */
    public ActivityCollection selectActivityCollectionById(Long activityCollectionId);

    /**
     * 新增活动收藏
     * @param activityCollection
     * @return
     */
    public Boolean insertActivityCollection(ActivityCollection activityCollection);

    /**
     * 修改活动收藏信息
     */
    public Boolean updateActivityCollection(ActivityCollection activityCollection);

    /**
     * 删除活动收藏
     */
    public Boolean deleteActivityCollectionById(Long activityCollectionId);

    /**
     * 批量删除活动收藏
     * @param activityCollectionIds
     * @return
     */
    public Boolean deleteActivityCollectionByIds(Long[]activityCollectionIds);

    /**
     * 分页查询
     * @param page
     * @param wrapper
     * @return
     */
    Page<ActivityCollection> searchActivityCollectionPage(Page<ActivityCollection> page, QueryWrapper<ActivityCollection> wrapper);


}

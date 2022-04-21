package com.toom.event_system.Controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.toom.event_system.Common.Result;
import com.toom.event_system.Entity.ActivityCollection;

import com.toom.event_system.Entity.PageInfo;
import com.toom.event_system.Service.ActivityCollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * 活动收藏
 * @author Toom
 */
@RestController
@RequestMapping("/collection")
public class ActivityCollectionController  extends BaseController{
    @Autowired
    private ActivityCollectionService activityCollectionService;

    /**
     * 查看是否收藏某活动
     */
    @GetMapping("/confirmCollect")
    public Result confirmCollect(@RequestParam("activityId") Long activityId,
                                    @RequestParam("userId") Long userId){
        QueryWrapper<ActivityCollection> wrapper = new QueryWrapper<>();
        Map<String, Long> map = new HashMap<>();
        map.put("user_id", userId);
        map.put("activity_id", activityId);
        wrapper.allEq(map);
        ActivityCollection activityCollection = activityCollectionService.getOne(wrapper);
        if (activityCollection != null){
            return Result.success(200);
        } else {
            return Result.success(500);
        }
    }

    /**
     * 分页条件查询活动收藏列表
     * @param pageCurrent
     * @param pageSize
     * @param activityName
     * @return
     */
    @GetMapping("/searchList")
    public PageInfo getSearchList(@RequestParam("pageCurrent") Integer pageCurrent,
                                  @RequestParam("pageSize") Integer pageSize,
                                  @RequestParam("activityName") String activityName,
                                  @RequestParam("userId") String userId){
        Page<ActivityCollection> page = new Page<>(pageCurrent, pageSize);
        QueryWrapper<ActivityCollection> wrapper = new QueryWrapper<>();
        if (!"".equals(activityName)){
            wrapper.like("activity_name", activityName);
        }
        if (userId != null){
            wrapper.eq("user_id", userId);
        }
        activityCollectionService.searchActivityCollectionPage(page, wrapper);
        PageInfo info = new PageInfo();
        info.setPageCurrent(pageCurrent);
        info.setPageSize(pageSize);
        info.setTotal(page.getTotal());
        info.setRows(page.getRecords());
        return info;
    }

    /**
     * 通过id查活动收藏
     */
    @RequestMapping("/selectById/{activityCollectionId}")
    public Result selectById(@PathVariable Long activityCollectionId) {
        return Result.success(activityCollectionService.selectActivityCollectionById(activityCollectionId));
    }

    /**
     * 新增活动收藏
     * @param activityCollection
     * @return
     */
    @PostMapping("/insert")
    public Result insertActivityCollection(@RequestBody ActivityCollection activityCollection) {
        activityCollection.setCreateBy("admin");
        return toAjax(activityCollectionService.insertActivityCollection(activityCollection));
    }

    /**
     * 修改活动收藏信息
     */
    @RequestMapping("/update")
    public Result updateActivityCollection(@RequestBody ActivityCollection activityCollection){
        activityCollection.setUpdateBy("admin");
        return toAjax(activityCollectionService.updateActivityCollection(activityCollection));
    }

    /**
     * 删除活动收藏
     */
    @DeleteMapping("/del/{activityCollectionIds}")
    public Result removeActivityCollection(@PathVariable Long[] activityCollectionIds){
        return toAjax(activityCollectionService.deleteActivityCollectionByIds(activityCollectionIds));
    }

    /**
     * 通过活动ID、用户ID删除活动收藏
     */
    @DeleteMapping("/delByAUId")
    public Result removeByActivityUserId(@RequestParam("activityId") Long activityId,
                                         @RequestParam("userId") Long userId) {
        QueryWrapper<ActivityCollection> wrapper = new QueryWrapper<>();
        Map<String, Long> map = new HashMap<>();
        map.put("user_id", userId);
        map.put("activity_id", activityId);
        wrapper.allEq(map); 
        return toAjax(activityCollectionService.remove(wrapper));
    }
}

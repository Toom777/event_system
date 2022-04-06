package com.toom.event_system.Controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.toom.event_system.Common.Result;
import com.toom.event_system.Entity.Activity;
import com.toom.event_system.Entity.PageInfo;
import com.toom.event_system.Service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 活动信息Controller
 *
 * @author Toom
 */
@RestController
@RequestMapping("/activity")
public class ActivityController extends BaseController{

    @Autowired
    private ActivityService activityService;



    /**
     * 获取活动标签
     */
    @GetMapping("/getActivityTags")
    public Result getActivityTags() {
        return Result.success(activityService.selectActivityTag());
    }

    /**
     * 分页
     * @param pageCurrent
     * @param pageSize
     * @param activityName
     * @param activityTag
     * @param beginTime
     * @param endTime
     * @return
     */
    @GetMapping("/searchList")
    public PageInfo getSearchList(@RequestParam("pageCurrent") Integer pageCurrent,
                                  @RequestParam("pageSize") Integer pageSize,
                                  @RequestParam("activityName") String activityName,
                                  @RequestParam("activityTag") String activityTag,
                                  @RequestParam("beginTime") @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") Date beginTime,
                                  @RequestParam("endTime") @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")Date endTime){
        Page<Activity> page = new Page<>(pageCurrent, pageSize);
        QueryWrapper<Activity> wrapper = new QueryWrapper<>();
        if (!"".equals(activityName)){
            wrapper.like("activity_name", activityName);
        }
        if (!"".equals(activityTag)){
            wrapper.like("activity_tag", activityTag);
        }
        if (beginTime != null) {
            wrapper.ge("begin_time", beginTime);
        }
        if (endTime != null) {
            wrapper.le("end_time", endTime);
        }
        //按照活动ID（活动生成）进行倒序查询
        wrapper.orderByDesc("activity_id");
        activityService.searchActivityPage(page, wrapper);

        /*根据当前时间对活动状态进行调整*/
        List<Activity> list  = page.getRecords();
        List<Activity> newList = new ArrayList<>();
        for (Activity ml : list){
            newList.add(activityService.statusChange(ml));
        }

        PageInfo info = new PageInfo();
        info.setPageCurrent(pageCurrent);
        info.setPageSize(pageSize);
        info.setTotal(page.getTotal());
        info.setRows(newList);
        return info;
    }

    /**
     * 通过id查活动
     */
    @RequestMapping("/selectById/{activityId}")
    public Result selectById(@PathVariable Long activityId) {
        return Result.success(activityService.selectUserById(activityId));
    }


    /**
     * 新增活动信息
     */
    @PostMapping("/insert")
    public Result add(@RequestBody Activity activity)
    {
        return toAjax(activityService.insertActivity(activity));
    }

    /**
     * 修改活动信息
     */
    @PutMapping("/update")
    public Result edit(@RequestBody Activity activity)
    {
        return toAjax(activityService.updateActivity(activity));
    }

    /**
     * 删除活动信息
     */
    @DeleteMapping("/del/{activityIds}")
    public Result remove(@PathVariable Long[] activityIds)
    {
        return toAjax(activityService.deleteActivityByActivityIds(activityIds));
    }
}

package com.toom.event_system.Controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.toom.event_system.Common.Result;
import com.toom.event_system.Entity.Confirmation;
import com.toom.event_system.Entity.PageInfo;

import com.toom.event_system.Service.ConfirmationService;
import com.toom.event_system.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 活动确认表 前端控制器
 *
 * @author Toom
 */
@RestController
@RequestMapping("/confirmation")
public class ConfirmationController extends BaseController{

    @Autowired
    private ConfirmationService confirmationService;

    @Autowired
    private UserService userService;

    @GetMapping("/searchListByUser")
    public PageInfo getsearchListByUser(@RequestParam("pageCurrent") Integer pageCurrent,
                                        @RequestParam("pageSize") Integer pageSize,
                                        @RequestParam("activityId") Long activityId,
                                        @RequestParam("userId") Long userId,
                                        @RequestParam("result") String result){
        Page<Confirmation> page = new Page<>(pageCurrent, pageSize);
        QueryWrapper<Confirmation> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", userId);
        if (activityId != null){
            wrapper.eq("activity_id", activityId);
        }
        if (!"".equals(result)){
            wrapper.eq("result", result);
        }
        confirmationService.searchConfirmationPage(page, wrapper);
        PageInfo info = new PageInfo();
        info.setPageCurrent(pageCurrent);
        info.setPageSize(pageSize);
        info.setTotal(page.getTotal());
        info.setRows(page.getRecords());
        return info;
    }


    /**
     * 通过id查活动确认信息
     */
    @RequestMapping("/selectById/{confirmationId}")
    public Result selectById(@PathVariable Long confirmationId) {
        return Result.success(confirmationService.searchConfirmationById(confirmationId));
    }


    /**
     * 分页查询
     * @param pageCurrent
     * @param pageSize
     * @param activityId
     * @param userId
     * @return
     */
    @GetMapping("/searchList")
    public PageInfo getSearchList(@RequestParam("pageCurrent") Integer pageCurrent,
                                  @RequestParam("pageSize") Integer pageSize,
                                  @RequestParam("activityId") Long activityId,
                                  @RequestParam("userId") Long userId){
        Page<Confirmation> page = new Page<>(pageCurrent, pageSize);
        QueryWrapper<Confirmation> wrapper = new QueryWrapper<>();
        if (activityId != null){
            wrapper.eq("activity_id", activityId);
        }
        if (userId != null){
            wrapper.eq("user_id", userId);
        }
        confirmationService.searchConfirmationPage(page, wrapper);
        PageInfo info = new PageInfo();
        info.setPageCurrent(pageCurrent);
        info.setPageSize(pageSize);
        info.setTotal(page.getTotal());
        info.setRows(page.getRecords());
        return info;
    }


    /**
     * 通过活动Id查找用户ID
     */
    @GetMapping("/confirmEnrollment")
    public Result confirmEnrollment(@RequestParam("activityId") Long activityId,
                                    @RequestParam("userId") Long userId){
        QueryWrapper<Confirmation> wrapper = new QueryWrapper<>();
        Map<String, Long> map = new HashMap<>();
        map.put("user_id", userId);
        map.put("activity_id", activityId);
        wrapper.allEq(map);
        Confirmation confirmation = confirmationService.getOne(wrapper);
        if (confirmation != null){
            return Result.success(200);
        } else {
            return Result.success(500);
        }
    }

    /**
     * 新增活动确认
     * @param confirmation
     * @return
     */
    @PostMapping("/insert")
    public Result insertConfirmation(@RequestBody Confirmation confirmation) {
        confirmation.setEnrollmentTime(LocalDateTime.now());
        confirmation.setCreateBy(userService.selectUserById(confirmation.getUserId()).getName());
        confirmation.setResult("0");
        return toAjax(confirmationService.insertConfirmation(confirmation));
    }

    /**
     * 修改活动确认信息
     */
    @RequestMapping("/update")
    public Result updateConfirmation(@RequestBody Confirmation confirmation){
        confirmation.setUpdateBy("admin");
        return toAjax(confirmationService.updateConfirmation(confirmation));
    }

    /**
     * 删除活动确认
     */
    @DeleteMapping("/del/{confirmationIds}")
    public Result removeConfirmation(@PathVariable Long[] confirmationIds){
        return toAjax(confirmationService.deleteConfirmationByIds(confirmationIds));
    }

}

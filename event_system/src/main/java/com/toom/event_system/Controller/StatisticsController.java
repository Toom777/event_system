package com.toom.event_system.Controller;


import com.toom.event_system.Common.Result;
import com.toom.event_system.Service.ActivityService;
import com.toom.event_system.Service.UserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 *  数据统计Controller
 * @author Toom
 */
@Api(value = "数据统计Controller", tags = {"数据统计操作接口"})
@RestController
@RequestMapping("/statistics")
public class StatisticsController extends BaseController{

    @Autowired
    private UserService userService;

    @Autowired
    private ActivityService activityService;


    /**
     * 数据统计： 用户数量 and 服务时长 and 活动项目数量
     */
    @GetMapping("/statisticsCount")
    public Result statisticsCount() {
        Map<String, Long> count = new HashMap<>();
        count.put("userNum", userService.countUser());
        count.put("hoursNum", userService.countHours());
        count.put("activityNum", activityService.countActivity());
        return Result.success(count);
    }
}

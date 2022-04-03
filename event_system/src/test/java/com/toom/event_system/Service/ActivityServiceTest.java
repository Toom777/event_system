package com.toom.event_system.Service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.toom.event_system.Entity.Activity;
import com.toom.event_system.Mapper.ActivityMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ActivityServiceTest {

    @Autowired
    private ActivityService activityService;

    @Autowired
    private ActivityMapper activityMapper;

    @Test
    void Test() throws ParseException {
        Activity activity = new Activity();

        String a = "2022-3-31 12:10:00";
        String b = "2022-3-31 12:12:00";
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        activity.setBeginTime(format.parse(a));
        activity.setEndTime(format.parse(b));
        activity.setStatus("0");



        activityService.statusChange(activity);

    }

    @Test
    void pageTest() {
        Page<Activity> page = new Page<>(1, 5);

        Page<Activity> mypage = activityMapper.selectPage(page, null);

        List<Activity> list  = mypage.getRecords();
        for (Activity ml : list){
            Activity updateActivity =  activityService.statusChange(ml);

            System.out.println(updateActivity);

        }


    }
}

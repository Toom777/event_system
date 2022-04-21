package com.toom.event_system.Service.Impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.toom.event_system.Entity.*;
import com.toom.event_system.Entity.Confirmation;
import com.toom.event_system.Mapper.ActivityMapper;
import com.toom.event_system.Mapper.ConfirmationMapper;
import com.toom.event_system.Mapper.UserMapper;
import com.toom.event_system.Service.ConfirmationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 活动确认表 服务实现类
 * @author Toom
 */
@Service
public class ConfirmationServiceImpl extends ServiceImpl<ConfirmationMapper, Confirmation> implements ConfirmationService {

    @Autowired
    private ConfirmationMapper confirmationMapper;

    @Autowired
    private ActivityMapper activityMapper;

    @Autowired
    private UserMapper userMapper;

    /**
     * 新增活动确认
     * @param confirmation
     * @return
     */
    @Override
    public Boolean insertConfirmation(Confirmation confirmation) {
        int row = confirmationMapper.insert(confirmation);
        if (row > 0) {
            Activity activity = activityMapper.selectById(confirmation.getActivityId());
            int count = activity.getConfirmCount();
            if (count < activity.getAllowCount()) {
                activity.setConfirmCount(++count);
                activityMapper.updateById(activity);
            }
            return true;
        } else {
            return false;
        }
    }

    /**
     * 修改活动确认信息
     */
    @Override
    public Boolean updateConfirmation(Confirmation confirmation) {
        confirmation.setUpdateTime(new Date());
        int row = confirmationMapper.updateById(confirmation);
        if (row > 0) {
            if ("2".equals(confirmation.getResult())) {
                Activity activity = activityMapper.selectById(confirmation.getActivityId());
                int count = activity.getConfirmCount();
                if (count > 0) {
                    activity.setConfirmCount(--count);
                    activityMapper.updateById(activity);
                }
            }
            return true;
        } else {
            return false;
        }

    }

    /**
     * 删除活动确认
     */
    @Override
    public Boolean deleteConfirmationById(Long confirmationId) {
        return confirmationMapper.deleteById(confirmationId) > 0 ? true : false;
    }

    /**
     * 批量删除活动确认
     * @param confirmationIds
     * @return
     */
    @Override
    public Boolean deleteConfirmationByIds(Long[] confirmationIds) {
        List<Long> list = new ArrayList<>();
        for (long id : confirmationIds) {
            list.add(id);
        }
        return confirmationMapper.deleteBatchIds(list) > 0 ? true : false;
    }

    /**
     * 分页查询
     * @param page
     * @param wrapper
     * @return
     */
    @Override
    public Page<Confirmation> searchConfirmationPage(Page<Confirmation> page, QueryWrapper<Confirmation> wrapper) {
        return confirmationMapper.selectPage(page, wrapper);
    }

    /**
     * 通过Id查询
     * @param confirmationId
     * @return
     */
    @Override
    public Confirmation searchConfirmationById(Long confirmationId) {
        return confirmationMapper.selectById(confirmationId);
    }

    /**
     * 更新用户活动后的服务时长和积分
     */
    @Override
    public Boolean updateUserHoursPoint(Long confirmationId) {
        Confirmation confirmation = confirmationMapper.selectById(confirmationId);
        if ("0".equals(confirmation.getTag())) {
            Activity activity = activityMapper.selectById(confirmation.getActivityId());
            User user = userMapper.selectById(confirmation.getUserId());
            /*获取相应活动积分*/
            int piont = activity.getActivityPoint();

            int minutes = (int) Duration.between(confirmation.getCheckIn(), confirmation.getCheckOut()).toMinutes();
            int hours = minutes / 60;
            int remainder = minutes % 60;
            if (remainder > 30) {
                hours++;
            }
            user.setHours(user.getHours() + hours);
            user.setPoints(user.getPoints() + piont);
            confirmation.setTag("1");
            userMapper.updateById(user);
            confirmationMapper.updateById(confirmation);
            return true;
        }
        return false;
    }

    /**
     * 批量更新积分时长
     */
    @Override
    public Boolean updateUserHoursPointByIds(Long[] confirmationIds) {
        for (long id : confirmationIds) {
            updateUserHoursPoint(id);
        }
        return true;
    }
}

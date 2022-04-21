package com.toom.event_system.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.toom.event_system.Entity.Community;
import com.toom.event_system.Entity.Confirmation;
import com.toom.event_system.Entity.Confirmation;
import com.baomidou.mybatisplus.extension.service.IService;
import com.toom.event_system.Entity.User;

/**
 * 活动确认表 服务类
 *
 * @author Toom
 */
public interface ConfirmationService extends IService<Confirmation> {


    /**
     * 新增活动确认
     * @param confirmation
     * @return
     */
    public Boolean insertConfirmation(Confirmation confirmation);

    /**
     * 修改活动确认信息
     */
    public Boolean updateConfirmation(Confirmation confirmation);

    /**
     * 删除活动确认
     */
    public Boolean deleteConfirmationById(Long confirmationId);

    /**
     * 批量删除活动确认
     * @param confirmationIds
     * @return
     */
    public Boolean deleteConfirmationByIds(Long[]confirmationIds);

    /**
     * 分页查询
     * @param page
     * @param wrapper
     */
    public Page<Confirmation> searchConfirmationPage(Page<Confirmation> page, QueryWrapper<Confirmation> wrapper);

    /**
     * 通过ID查询
     * @param confirmationId
     * @return
     */
    public Confirmation searchConfirmationById(Long confirmationId);

    /**
     * 更新用户活动后的服务时长和积分
     */
    public Boolean updateUserHoursPoint(Long confirmationId);

    /**
     * 批量更新积分时长
     */
    public Boolean updateUserHoursPointByIds(Long[] confirmationIds);
}

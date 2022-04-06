package com.toom.event_system.Service.Impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.toom.event_system.Entity.Community;
import com.toom.event_system.Entity.Confirmation;
import com.toom.event_system.Entity.Confirmation;
import com.toom.event_system.Entity.User;
import com.toom.event_system.Mapper.ConfirmationMapper;
import com.toom.event_system.Service.ConfirmationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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


    /**
     * 新增活动确认
     * @param confirmation
     * @return
     */
    @Override
    public Boolean insertConfirmation(Confirmation confirmation) {
        int row = confirmationMapper.insert(confirmation);
        return row > 0 ? true : false;
    }

    /**
     * 修改活动确认信息
     */
    @Override
    public Boolean updateConfirmation(Confirmation confirmation) {
        confirmation.setUpdateTime(new Date());
        return confirmationMapper.updateById(confirmation) > 0 ? true : false;
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
}

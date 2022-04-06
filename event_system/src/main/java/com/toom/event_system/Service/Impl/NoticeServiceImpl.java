package com.toom.event_system.Service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.toom.event_system.Entity.Notice;
import com.toom.event_system.Entity.User;
import com.toom.event_system.Mapper.NoticeMapper;
import com.toom.event_system.Service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 公告 服务实现类
 *
 * @author Toom
 */
@Service
public class NoticeServiceImpl extends ServiceImpl<NoticeMapper, Notice> implements NoticeService {

    @Autowired
    private NoticeMapper noticeMapper;
    /**
     * 分页查询
     * @param page
     * @param wrapper
     * @return
     */
    @Override
    public Page<Notice> searchNoticePage(Page<Notice> page, QueryWrapper<Notice> wrapper) {
        return noticeMapper.selectPage(page, wrapper);
    }

    /**
     * 新增公告
     * @param notice
     * @return
     */
    @Override
    public Boolean insertNotice(Notice notice) {
        return noticeMapper.insert(notice) > 0 ? true : false;
    }

    /**
     * 修改公告
     * @param notice
     * @return
     */
    @Override
    public Boolean updateNotice(Notice notice) {
        notice.setUpdateTime(new Date());
        return noticeMapper.updateById(notice) > 0 ? true : false;
    }

    /**
     * 删除公告
     * @param noticeId
     * @return
     */
    @Override
    public Boolean deleteNoticeById(Long noticeId) {
        return noticeMapper.deleteById(noticeId) > 0 ? true : false;
    }

    /**
     * 批量删除公告
     * @param noticeIds
     * @return
     */
    @Override
    public Boolean deleteNoticeByIds(Long[] noticeIds) {
        List<Long> list = new ArrayList<>();
        for (long id : noticeIds) {
            list.add(id);
        }
        return noticeMapper.deleteBatchIds(list) > 0 ? true : false;
    }

    /**
     * 通过ID找公告
     * @param noticeId
     * @return
     */
    @Override
    public Notice selectNoticeById(Long noticeId) {
        return noticeMapper.selectById(noticeId);
    }
}

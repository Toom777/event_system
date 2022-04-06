package com.toom.event_system.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.toom.event_system.Entity.Community;
import com.toom.event_system.Entity.Notice;

/**
 * 公告表 服务类
 * @author Toom
 */
public interface NoticeService extends IService<Notice> {
    /**
     * 分页查询
     * @param page
     * @param wrapper
     */
    Page<Notice> searchNoticePage(Page<Notice> page, QueryWrapper<Notice> wrapper);

    /**
     * 新增公告
     * @param notice
     * @return
     */
    public Boolean insertNotice(Notice notice);

    /**
     * 修改公告信息
     */
    public Boolean updateNotice(Notice notice);

    /**
     * 删除公告
     */
    public Boolean deleteNoticeById(Long noticeId);

    /**
     * 批量删除公告
     * @param noticeIds
     * @return
     */
    public Boolean deleteNoticeByIds(Long[]noticeIds);

    /**
     *  通过ID查找公告
     */
    public Notice selectNoticeById(Long noticeId);
}

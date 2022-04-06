package com.toom.event_system.Controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.toom.event_system.Common.Result;

import com.toom.event_system.Entity.Notice;
import com.toom.event_system.Entity.PageInfo;
import com.toom.event_system.Service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author Toom
 */
@RestController
@RequestMapping("/notice")
public class NoticeController extends BaseController{

    @Autowired
    private NoticeService noticeService;

    /**
     * 分页条件查询公告列表
     * @param pageCurrent
     * @param pageSize
     * @param editor
     * @param noticeTitle
     * @return
     */
    @GetMapping("/searchList")
    public PageInfo getSearchList(@RequestParam("pageCurrent") Integer pageCurrent,
                                  @RequestParam("pageSize") Integer pageSize,
                                  @RequestParam("editor") String editor,
                                  @RequestParam("noticeTitle") String noticeTitle){
        Page<Notice> page = new Page<>(pageCurrent, pageSize);
        QueryWrapper<Notice> wrapper = new QueryWrapper<>();
        if (!"".equals(editor)){
            wrapper.like("editor", editor);
        }
        if (!"".equals(noticeTitle)){
            wrapper.like("notice_title", noticeTitle);
        }
        //倒序
        wrapper.orderByDesc("notice_id");
        noticeService.searchNoticePage(page, wrapper);
        PageInfo info = new PageInfo();
        info.setPageCurrent(pageCurrent);
        info.setPageSize(pageSize);
        info.setTotal(page.getTotal());
        info.setRows(page.getRecords());
        return info;
    }

    /**
     * 通过Id查公告
     */
    @RequestMapping("/selectById/{noticeId}")
    public Result selectById(@PathVariable Long noticeId) {
        return Result.success(noticeService.selectNoticeById(noticeId));
    }

    /**
     * 新增公告
     * @param notice
     * @return
     */
    @PostMapping("/insert")
    public Result insertNotice(@RequestBody Notice notice) {
        notice.setCreateBy("admin");
        return toAjax(noticeService.insertNotice(notice));
    }

    /**
     * 修改公告
     */
    @RequestMapping("/update")
    public Result updateNotice(@RequestBody Notice notice){
        notice.setUpdateBy("admin");
        return toAjax(noticeService.updateNotice(notice));
    }

    /**
     * 删除公告
     */
    @DeleteMapping("/del/{noticeIds}")
    public Result removeNotice(@PathVariable Long[] noticeIds){
        return toAjax(noticeService.deleteNoticeByIds(noticeIds));
    }

}

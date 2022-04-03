package com.toom.event_system.Controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.toom.event_system.Common.Result;
import com.toom.event_system.Entity.Community;


import com.toom.event_system.Entity.PageInfo;
import com.toom.event_system.Entity.User;
import com.toom.event_system.Service.CommunityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 社区管理控制层
 * @author Toom
 */
@RestController
@RequestMapping("/community")
public class CommunityController extends BaseController {

    @Autowired
    private CommunityService communityService;

    /**
     *  测试
     */
    @RequestMapping("/find")
    public Result find(){
        return Result.success();
    }

    /**
     * 获取所有社区
     */
    @GetMapping("/selectAll")
    public Result selectAll(){
        return Result.success(communityService.selectCommunityAll());
    }


    /**
     * 分页条件查询社区列表
     * @param pageCurrent
     * @param pageSize
     * @param communityName
     * @return
     */
    @GetMapping("/searchList")
    public PageInfo getSearchList(@RequestParam("pageCurrent") Integer pageCurrent,
                                  @RequestParam("pageSize") Integer pageSize,
                                  @RequestParam("communityName") String communityName){
        Page<Community> page = new Page<>(pageCurrent, pageSize);
        QueryWrapper<Community> wrapper = new QueryWrapper<>();
        if (!"".equals(communityName)){
            wrapper.like("community_name", communityName);
        }
        communityService.searchCommunityPage(page, wrapper);
        PageInfo info = new PageInfo();
        info.setPageCurrent(pageCurrent);
        info.setPageSize(pageSize);
        info.setTotal(page.getTotal());
        info.setRows(page.getRecords());
        return info;
    }

    /**
     * 获取所有社区名称
     */
    @GetMapping("/selectAllName")
    public Result selectAllName(){
        return Result.success(communityService.selectCommunityNameAll());
    }

    /**
     * 获取所有社区名称+ID （plus版）
     */
    @GetMapping("/selectIdAndName")
    public Result selectAllIdAndName() { return Result.success(communityService.selectCommunityIdAndNameAll());}


    /**
     * 通过id查社区
     */
    @RequestMapping("/selectById/{communityId}")
    public Result selectById(@PathVariable Long communityId) {
        return Result.success(communityService.selectCommunityById(communityId));
    }

    /**
     * 通过名字查社区ID
     * @param communityName
     * @return
     */
    @GetMapping("/selectByName/{communityName}")
    public Result selectByName(@PathVariable String communityName){
        return Result.success(communityService.selectCommunityByName(communityName));
    }


    /**
     * 新增社区
     * @param community
     * @return
     */
    @PostMapping("/insert")
    public Result insertCommunity(@RequestBody Community community) {
        community.setCreateBy("admin");
        return toAjax(communityService.insertCommunity(community));
    }

    /**
     * 修改社区信息
     */
    @RequestMapping("/update")
    public Result updateCommunity(@RequestBody Community community){
        community.setUpdateBy("admin");
        return toAjax(communityService.updateCommunity(community));
    }

    /**
     * 删除社区
     */
    @DeleteMapping("/del/{communityIds}")
    public Result removeCommunity(@PathVariable Long[] communityIds){
        return toAjax(communityService.deleteCommunityByIds(communityIds));
    }
}

package com.toom.event_system.Service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.toom.event_system.Entity.Community;


import java.util.List;
import java.util.Map;

/**
 * @author Toom
 */
public interface CommunityService extends IService<Community> {

    /**
     * 获取该社区下所有用户
     */


    /**
     * 获取所有社区
     */
    public List<Community> selectCommunityAll();

    /**
     *  通过ID查找社区
     */
    public Community selectCommunityById(Long communityId);


    /**
     * 通过名字查社区ID
     * @param communityName
     * @return
     */
    public Long selectCommunityByName(String communityName);

    /**
     * 获取所有社区的名字
     * @return
     */
    public List<String> selectCommunityNameAll();


    /**
     * 获取所有社区id和名称
     * @return
     */
    public List<Map<String, Object>> selectCommunityIdAndNameAll();

    /**
     * 新增社区
     * @param community
     * @return
     */
    public Boolean insertCommunity(Community community);

    /**
     * 修改社区信息
     */
    public Boolean updateCommunity(Community community);

    /**
     * 删除社区
     */
    public Boolean deleteCommunityById(Long communityId);

    /**
     * 批量删除社区
     * @param communityIds
     * @return
     */
    public Boolean deleteCommunityByIds(Long[]communityIds);

    /**
     * 分页查询
     * @param page
     * @param wrapper
     * @return
     */
    Page<Community> searchCommunityPage(Page<Community> page, QueryWrapper<Community> wrapper);
}


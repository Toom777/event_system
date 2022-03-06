package com.toom.event_system.Service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.toom.event_system.Entity.Community;


import java.util.List;

/**
 * @author Toom
 */
public interface CommunityService extends IService<Community> {

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


}


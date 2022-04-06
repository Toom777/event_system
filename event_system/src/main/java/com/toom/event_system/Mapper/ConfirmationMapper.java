package com.toom.event_system.Mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.toom.event_system.Entity.Confirmation;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.toom.event_system.Entity.Vo.ConfirmActivityUserVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 活动确认表 Mapper 接口
 *
 * @author Toom
 */
@Repository
public interface ConfirmationMapper extends BaseMapper<Confirmation> {

    /**
     * 返回活动名字和用户姓名
     * @param confirmationId
     * @return
     */
    @Select(" SELECT c.confirmationt_id, c.activity_id, c.user_id, u.name, a.activity_name FROM `confirmation` c, user u, activity a where a.activity_id = c.activity_id and u.user_id = c.user_id and c.confirmationt_id = 1")
    ConfirmActivityUserVo nameList(Long confirmationId);
}

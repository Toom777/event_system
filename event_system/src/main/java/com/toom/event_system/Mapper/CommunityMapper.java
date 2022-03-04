package com.toom.event_system.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.toom.event_system.Entity.Community;
import com.toom.event_system.Entity.User;
import org.springframework.stereotype.Repository;

/**
 * 社区表接口
 * @author Toom
 */
@Repository
public interface CommunityMapper  extends BaseMapper<Community> {
}

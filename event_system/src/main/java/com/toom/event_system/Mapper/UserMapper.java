package com.toom.event_system.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.toom.event_system.Entity.User;
import org.springframework.stereotype.Repository;

/**
 * 用户接口
 * @author Toom
 */
@Repository
public interface UserMapper extends BaseMapper<User> {

}

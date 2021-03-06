package com.toom.event_system.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.toom.event_system.Entity.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 用户接口
 * @author Toom
 */
@Repository
public interface UserMapper extends BaseMapper<User> {


    /**
     * 统计所有用户服务时长
     * @return
     */
    @Select("SELECT SUM(hours) FROM `user`")
    Long hoursCount();
}

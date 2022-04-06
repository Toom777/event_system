package com.toom.event_system.Mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.toom.event_system.Entity.Notice;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * 公告表 Mapper 接口
 * @author Toom
 */
@Repository
public interface NoticeMapper extends BaseMapper<Notice> {

}

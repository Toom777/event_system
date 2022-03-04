package com.toom.event_system.Entity;


import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

@Data
@TableName(value = "community")
public class Community extends BaseEntity {

    /** 社区ID */
    @TableId(value = "community_id", type = IdType.AUTO)
    private Long communityId;

    /** 社区名称 */
    @TableField("community_name")
    private String communityName;

    /** 状态（0正常 1停用） */
    @TableField(value = "status")
    private String status;
}

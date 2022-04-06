package com.toom.event_system.Entity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 活动收藏表
 * @author Toom
 */
@Data
@TableName(value = "collection")
public class ActivityCollection extends BaseEntity{

    /** 收藏ID */
    @ApiModelProperty("收藏ID")
    @TableId(value = "collection_id", type = IdType.AUTO)
    private Long collectionId;

    @ApiModelProperty("用户ID")
    @TableField("user_id")
    private Long userId;

    @ApiModelProperty("活动ID")
    @TableField("activity_id")
    private Long activityId;

    @ApiModelProperty("活动名称")
    @TableField("activity_name")
    private String activityName;
}

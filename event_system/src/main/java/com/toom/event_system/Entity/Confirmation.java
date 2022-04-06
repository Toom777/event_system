package com.toom.event_system.Entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * 活动确认表
 * @author Toom
 */
@Data
@TableName("confirmation")
@ApiModel(value = "Confirmation对象", description = "活动确认表")
public class Confirmation extends BaseEntity {

    @ApiModelProperty("确认ID")
    @TableId(value = "confirmationt_id", type = IdType.AUTO)
    private Long confirmationtId;

    @ApiModelProperty("活动ID")
    @TableField("activity_id")
    private Long activityId;

    @ApiModelProperty("用户ID")
    @TableField("user_id")
    private Long userId;

    @ApiModelProperty("报名时间")
    @TableField("enrollment_time")
    private LocalDateTime enrollmentTime;

    @ApiModelProperty("审核结果（0待审核 1通过 2不通过）")
    @TableField("result")
    private String result;

    @ApiModelProperty("签到时间")
    @TableField("check_in")
    private LocalDateTime checkIn;

    @ApiModelProperty("签退时间")
    @TableField("check_out")
    private LocalDateTime checkOut;


}

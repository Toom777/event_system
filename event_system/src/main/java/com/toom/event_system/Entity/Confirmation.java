package com.toom.event_system.Entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 活动确认表
 * @author Toom
 */
@Data
@TableName("confirmation")
@ApiModel(value = "Confirmation对象", description = "活动确认表")
public class Confirmation extends BaseEntity {

    @ApiModelProperty("确认ID")
    @TableId(value = "confirmation_id", type = IdType.AUTO)
    private Long confirmationId;

    @ApiModelProperty("活动ID")
    @TableField("activity_id")
    private Long activityId;

    @ApiModelProperty("用户ID")
    @TableField("user_id")
    private Long userId;

    @ApiModelProperty("活动名称")
    @TableField("activity_name")
    private String activityName;

    @ApiModelProperty("用户名字 ")
    @TableField("user_name")
    private String userName;

    @ApiModelProperty("报名时间")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField("enrollment_time")
    private LocalDateTime enrollmentTime;

    @ApiModelProperty("审核结果（0待审核 1通过 2不通过）")
    @TableField("result")
    private String result;

    @ApiModelProperty("签到时间")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField("check_in")
    private LocalDateTime checkIn;

    @ApiModelProperty("签退时间")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField("check_out")
    private LocalDateTime checkOut;

    @ApiModelProperty("是否已经加分（0否 1是）")
    @TableField("tag")
    private String tag;

}

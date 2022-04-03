package com.toom.event_system.Entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 活动信息表
 *
 * @author Toom
 */
@Data
@TableName("activity")
@ApiModel(value = "Activity对象", description = "活动信息表")
public class Activity extends BaseEntity {

    @ApiModelProperty("活动ID")
    @TableId(value = "activity_id", type = IdType.AUTO)
    private Long activityId;

    @ApiModelProperty("发布人ID")
    @TableField("user_id")
    private Long userId;

    @ApiModelProperty("活动名称")
    @TableField("activity_name")
    private String activityName;

    @ApiModelProperty("活动标签")
    @TableField("activity_tag")
    private String activityTag;

    @ApiModelProperty("活动内容")
    @TableField("activity_content")
    private String activityContent;

    @ApiModelProperty("报名要求")
    @TableField("requirement")
    private String requirement;

    @ApiModelProperty("可报名人数")
    @TableField("allow_count")
    private Integer allowCount;

    @ApiModelProperty("已报名人数")
    @TableField("confirm_count")
    private Integer confirmCount;

    @ApiModelProperty("报名截止时间")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField("deadline")
    private Date deadline;

    @ApiModelProperty("联系人")
    @TableField("contact_name")
    private String contactName;

    @ApiModelProperty("联系电话")
    @TableField("contact_phone")
    private String contactPhone;

    @ApiModelProperty("活动地点")
    @TableField("activitiy_location")
    private String activitiyLocation;

    @ApiModelProperty("活动积分")
    @TableField("activitiy_point")
    private Integer activitiyPoint;

    @ApiModelProperty("图片")
    @TableField("picture")
    private String picture;

    @ApiModelProperty("活动开始时间")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField("begin_time")
    private Date beginTime;

    @ApiModelProperty("活动结束时间")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField("end_time")
    private Date endTime;

    @ApiModelProperty("活动状态（0未开始 1进行中 2已结束）")
    @TableField("status")
    private String status;


}

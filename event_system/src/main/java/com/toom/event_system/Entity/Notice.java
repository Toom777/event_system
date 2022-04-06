package com.toom.event_system.Entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 公告
 * @author Toom
 */
@Data
public class Notice extends BaseEntity{
    @ApiModelProperty("公告ID")
    @TableId(value = "notice_id", type = IdType.AUTO)
    private Long noticeId;

    @ApiModelProperty("发布人名字")
    @TableField("editor")
    private String editor;

    @ApiModelProperty("公告标题")
    @TableField("notice_title")
    private String noticeTitle;

    @ApiModelProperty("公告内容")
    @TableField(value = "notice_content")
    private String noticeContent;

    @ApiModelProperty("公告状态（0正常 1关闭）")
    @TableField("status")
    private String status;
}

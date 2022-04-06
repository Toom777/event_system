package com.toom.event_system.Entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 资讯表
 * @author Toom
 */
@Data
@TableName("news")
@ApiModel(value = "News对象", description = "资讯表")
public class News extends BaseEntity{
    @ApiModelProperty("资讯ID")
    @TableId(value = "news_id", type = IdType.AUTO)
    private Long newsId;

    @ApiModelProperty("发布人")
    @TableField("editor")
    private String editor;

    @ApiModelProperty("资讯标题")
    @TableField("news_title")
    private String newsTitle;

    @ApiModelProperty("资讯类型")
    @TableField("news_type")
    private String newsType;

    @ApiModelProperty("资讯内容")
    @TableField("news_content")
    private String newsContent;

    @ApiModelProperty("图片")
    @TableField("picture")
    private String picture;

    @ApiModelProperty("资讯状态（0正常 1关闭）")
    @TableField("status")
    private String status;
}

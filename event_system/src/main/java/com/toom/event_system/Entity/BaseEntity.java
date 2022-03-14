package com.toom.event_system.Entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 实体类基类
 * @author Toom
 */
@Data
public class BaseEntity implements Serializable {

    /** 创建者 */
    @ApiModelProperty("创建者")
    @TableField("create_by")
    private String createBy;

    /** 创建时间 */
    @ApiModelProperty("创建时间")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(value = "create_time",fill = FieldFill.INSERT)
    private Date createTime;

    /** 更新者 */
    @ApiModelProperty("更新者")
    @TableField("update_by")
    private String updateBy;

    /** 更新时间 */
    @ApiModelProperty("更新时间")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    /** 备注 */
    @ApiModelProperty("备注")
    @TableField("remark")
    private String remark;

}

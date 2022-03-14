package com.toom.event_system.Entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 菜单表
 * @author Toom
 */
@Data
@TableName("menu")
public class Menu extends BaseEntity{
    @ApiModelProperty("菜单ID")
    @TableId(value = "menu_id", type = IdType.AUTO)
    private Long menuId;

    @ApiModelProperty("菜单名称")
    @TableField("menu_name")
    private String menuName;

    @ApiModelProperty("父菜单ID")
    @TableField("parent_id")
    private Long parentId;

    @ApiModelProperty("显示顺序")
    @TableField("order_num")
    private Integer orderNum;

    @ApiModelProperty("路由地址")
    @TableField("path")
    private String path;

    @ApiModelProperty("组件路径")
    @TableField("component")
    private String component;

    @ApiModelProperty("菜单类型（M目录 C菜单 F按钮）")
    @TableField("menu_type")
    private String menuType;

    @ApiModelProperty("菜单显示（0显示 1隐藏）")
    @TableField("visible")
    private String visible;

    @ApiModelProperty("菜单状态（0正常 1停用）")
    @TableField("status")
    private String status;

    @ApiModelProperty("权限标识")
    @TableField("perms")
    private String perms;

    @ApiModelProperty("菜单图标")
    @TableField("icon")
    private String icon;

}

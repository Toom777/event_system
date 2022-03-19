package com.toom.event_system.Entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户表实体类
 * @author Toom
 */
@Data
@TableName(value = "user")
public class User extends BaseEntity  {
    /** 用户ID */
    @TableId(type = IdType.AUTO)
    private Long userId;

    /** 社区ID */
    private Long communityId;

    /** 用户账号 */
    private String username;

    /** 真实姓名 */
    private String name;

    /** 密码 */
    private String password;

    /** 用户类型（01系统用户，02社区管理员，03普通用户） */
    private String userType;

    /** 用户邮箱 */
    private String email;

    /** 手机号码 */
    private String phone;

    /** 用户性别（0男 1女） */
    private String sex;

    /** 政治面貌 */
    private String politicalStatus;

    /** 职业 */
    private String career;

    /** 出生日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date birthday;

    /** 积分 */
    private Long points;

    /** 星级 */
    private Long sating;

    /** 服务时长 */
    private Long hours;

    /** 头像地址 */
    private String avatar;

    /** 帐号状态（0正常 1停用） */
    private String status;

    /** 删除标志（0代表存在 2代表删除） */
    private String delFlag;

    /** 最后登录IP */
    private String loginIp;

    /** 最后登录时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date loginDate;
}

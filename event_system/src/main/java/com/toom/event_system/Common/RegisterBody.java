package com.toom.event_system.Common;

import lombok.Data;

import java.util.Date;

/**
 * 用户注册对象
 * @author Toom
 */
@Data
public class RegisterBody{

    /** 用户名 */
    private String username;

    /** 密码 */
    private String password;

    /** 真实姓名 */
    private String name;

    /** 性别 */
    private String sex;

    /** 手机号 */
    private String phone;

    /** 社区 */
    private String community;

    /** 邮箱 */
    private String email;

    /** 职业 */
    private String career;

    /** 政治面貌 */
    private String politicalStatus;

    /** 出生日期 */
    private Date birthday;

}

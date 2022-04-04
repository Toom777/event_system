package com.toom.event_system.Entity;

import lombok.Data;

/**
 * 用户角色关联
 * @author Toom
 */
@Data
public class UserRole {

    /** 用户ID */
    private Long userId;

    /** 角色ID */
    private Long roleId;
}

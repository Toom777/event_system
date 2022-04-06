package com.toom.event_system.Entity.Vo;

import com.toom.event_system.Entity.BaseEntity;
import lombok.Data;

/**
 * 用于返回用户名、活动名
 * @author Toom
 */
@Data
public class ConfirmActivityUserVo extends BaseEntity {

    private Long confirmationId;

    private Long activityId;

    private Long userId;

    private String name;

    private String activityName;
}

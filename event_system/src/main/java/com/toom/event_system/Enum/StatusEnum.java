package com.toom.event_system.Enum;

import com.baomidou.mybatisplus.annotation.EnumValue;

/**
 * Status状态枚举类
 *
 */
public enum  StatusEnum {
    AVAILABLE(0, "正常"),
    DISABLE(1, "禁用");

    StatusEnum(Integer code, String msg){
        this.code = code;
        this.msg = msg;
    }

    @EnumValue
    private Integer code;
    private String msg;
}

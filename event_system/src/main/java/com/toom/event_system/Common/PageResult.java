package com.toom.event_system.Common;

import lombok.Data;

import java.io.Serializable;

/**
 * 分页返回
 */
@Data
public class PageResult implements Serializable {
    /** 是否成功 */
    private boolean flag;

    /** 状态码 */
    private Integer code;

    /** 返回内容 */
    private String message;

    /** 数据对象 */
    private Object data;

    /** 总计 */
    private Long total;

    public PageResult() {
    }

    /**
     * @param flag  请求是否成功
     * @param code  状态码
     * @param message   消息提示
     * @param data      结果集列表
     * @param total     总条数
     * @return :
     */
    public PageResult(boolean flag, Integer code, String message, Object data, Long total) {
        this.flag = flag;
        this.code = code;
        this.message = message;
        this.data = data;
        this.total = total;
    }
}

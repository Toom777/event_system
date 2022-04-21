package com.toom.event_system.Entity;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 分页数据对象
 * @author Toom
 */
@Data
public class PageInfo implements Serializable {

    /** 当前页 */
    private Integer pageCurrent;

    /** 每页数据条数 */
    private Integer pageSize;

    /** 总记录数 */
    private long total;

    /** 列表数据 */
    private List<?> rows;

    /** 消息状态码 */
    private int code = 200;

    /** 消息内容 */
    private String msg = "返回成功";

    public PageInfo(){}

    public PageInfo(List<?> list, int total){
        this.rows = list;
        this.total = total;
    }
}

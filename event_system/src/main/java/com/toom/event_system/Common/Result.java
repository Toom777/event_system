package com.toom.event_system.Common;

import lombok.Data;

import java.util.HashMap;


/**
 * 结果返回
 * @author Toom
 */
@Data
public class Result extends HashMap<String, Object>
{
    /** 状态码 */
    public static final String CODE_TAG = "code";

    /** 返回内容 */
    public static final String MSG_TAG = "message";

    /** 数据对象 */
    public static final String DATA_TAG = "data";

    /**
     * 初始化一个新创建的 Result 对象，使其表示一个空消息。
     */
    public Result()
    {
    }

    /**
     * 初始化一个新创建的 Result 对象
     *
     * @param code 状态码
     * @param message 返回内容
     */
    public Result(int code, String message)
    {
        super.put(CODE_TAG, code);
        super.put(MSG_TAG, message);
    }

    /**
     * 初始化一个新创建的 Result 对象
     *
     * @param code 状态码
     * @param message 返回内容
     * @param data 数据对象
     */
    public Result(int code, String message, Object data)
    {
        super.put(CODE_TAG, code);
        super.put(MSG_TAG, message);
        if (data != null)
        {
            super.put(DATA_TAG, data);
        }
    }

    /**
     * 返回成功消息
     *
     * @return 成功消息
     */
    public static Result success()
    {
        return Result.success("操作成功");
    }

    /**
     * 返回成功数据
     *
     * @return 成功消息
     */
    public static Result success(Object data)
    {
        return Result.success("操作成功", data);
    }

    /**
     * 返回成功消息
     *
     * @param message 返回内容
     * @return 成功消息
     */
    public static Result success(String message)
    {
        return Result.success(message, null);
    }

    /**
     * 返回成功消息
     *
     * @param message 返回内容
     * @param data 数据对象
     * @return 成功消息
     */
    public static Result success(String message, Object data)
    {
        return new Result(HttpStatus.SUCCESS, message, data);
    }

    /**
     * 返回错误消息
     *
     * @return
     */
    public static Result error()
    {
        return Result.error("操作失败");
    }

    /**
     * 返回错误消息
     *
     * @param message 返回内容
     * @return 警告消息
     */
    public static Result error(String message)
    {
        return Result.error(message, null);
    }

    /**
     * 返回错误消息
     *
     * @param message 返回内容
     * @param data 数据对象
     * @return 警告消息
     */
    public static Result error(String message, Object data)
    {
        return new Result(HttpStatus.ERROR, message, data);
    }

    /**
     * 返回错误消息
     *
     * @param code 状态码
     * @param message 返回内容
     * @return 警告消息
     */
    public static Result error(int code, String message)
    {
        return new Result(code, message, null);
    }

    /**
     * 方便链式调用
     *
     * @param key 键
     * @param value 值
     * @return 数据对象
     */
    @Override
    public Result put(String key, Object value)
    {
        super.put(key, value);
        return this;
    }
}

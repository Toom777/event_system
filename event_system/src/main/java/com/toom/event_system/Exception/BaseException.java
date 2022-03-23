package com.toom.event_system.Exception;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import lombok.Data;

/**
 * 全局异常处理
 * @author Toom
 */

@Data
public class BaseException extends RuntimeException{

    /**  所属模块 */
    private String module;

    /** 错误码 */
    private String code;

    /** 错误码对应的参数 */
    private Object[] args;

    /**  错误信息 */
    private String message;

    public BaseException(String module, String code, Object[] args, String message)
    {
        this.module = module;
        this.code = code;
        this.args = args;
        this.message = message;
    }

    public BaseException(String module, String code, Object[] args)
    {
        this(module, code, args, null);
    }

    public BaseException(String module, String defaultMessage)
    {
        this(module, null, null, defaultMessage);
    }

    public BaseException(String code, Object[] args)
    {
        this(null, code, args, null);
    }

    public BaseException(String defaultMessage)
    {
        this(null, null, null, defaultMessage);
    }

}

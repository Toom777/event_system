package com.toom.event_system.Controller;


import com.toom.event_system.Common.Result;
import io.swagger.annotations.Api;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import java.beans.PropertyEditorSupport;
import java.util.Date;

/**
 *  Web通用数据处理
 */
@Api(value = "BaseController", tags = {"Result统一操作接口"})
public class BaseController {
    /**
     * 将前台传递过来的日期格式的字符串，自动转化为Date类型
     */
/*    @InitBinder
    public void initBinder(WebDataBinder binder)
    {
        // Date 类型转换
        binder.registerCustomEditor(Date.class, new PropertyEditorSupport()
        {
            @Override
            public void setAsText(String text)
            {
                setValue(DateUtils.parseDate(text));
            }
        });
    }*/

    /**
     * 返回成功
     */
    public Result success()
    {
        return Result.success();
    }

    /**
     * 返回失败消息
     */
    public Result error()
    {
        return Result.error();
    }

    /**
     * 返回成功消息
     */
    public Result success(String message)
    {
        return Result.success(message);
    }

    /**
     * 返回失败消息
     */
    public Result error(String message)
    {
        return Result.error(message);
    }

    /**
     * 响应返回结果
     *
     * @return 操作结果
     */
    protected Result toAjax(Boolean flag)
    {
        return flag ? Result.success() : Result.error();
    }

}

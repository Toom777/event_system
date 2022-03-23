package com.toom.event_system.Exception;

/**
 * 验证码异常
 * @author Toom
 */
public class CaptchaException extends BaseException{

    public CaptchaException(String module, String code, Object[] args, String message) {
        super(module, code, args, message);
    }

}

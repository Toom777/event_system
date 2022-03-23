package com.toom.event_system.Service;

import com.toom.event_system.Entity.CaptchaEntity;

/**
 * 验证码服务接口
 */
public interface CaptchaService {
    /**
     * Kaptcha生成验证码
     * @return
     */
    public CaptchaEntity createCaptcha();

    /**
     * 验证码校验
     * @param code
     * @param uuid
     */
    public int validateCaptcha(String code, String uuid);
}

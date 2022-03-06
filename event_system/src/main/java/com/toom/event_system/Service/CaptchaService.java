package com.toom.event_system.Service;

import com.toom.event_system.Entity.CaptchaEntity;

/**
 * 验证码服务接口
 */
public interface CaptchaService {
    /**
     * Kaptcha生成验证码服务层接口
     * @return
     */
    public CaptchaEntity createCaptcha();
}

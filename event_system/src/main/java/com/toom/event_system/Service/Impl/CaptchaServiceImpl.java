package com.toom.event_system.Service.Impl;

import com.google.code.kaptcha.Producer;
import com.toom.event_system.Common.Utils.RedisUtils;
import com.toom.event_system.Entity.CaptchaEntity;
import com.toom.event_system.Exception.CaptchaException;
import com.toom.event_system.Service.CaptchaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author Toom
 */
@Service
public class CaptchaServiceImpl implements CaptchaService {


    @Resource(name = "captchaProducerMath")
    private Producer captchaProducerMath;

    @Autowired
    private RedisUtils redisUtils;

    /**
     * 创建验证码
     * @return
     */
    @Override
    public CaptchaEntity createCaptcha() {

        //定义实体
        CaptchaEntity captchaEntity = new CaptchaEntity();

        // 生成文本
        String producerText = captchaProducerMath.createText();

        // 设置验证码字符
        captchaEntity.setText(producerText.substring(0, producerText.indexOf("@")));

        // 设置验证码答案
        captchaEntity.setCode(producerText.substring(producerText.indexOf("@") + 1));

        // 设置验证码图片
        captchaEntity.setImage(captchaProducerMath.createImage(captchaEntity.getText()));


        return captchaEntity;
    }

    /**
     * 验证码校验
     */
    @Override
    public int validateCaptcha(String code, String uuid) {
        String verifyKey = "captchaCode:" + uuid;
        String captcha = (String) redisUtils.get(verifyKey);
        System.out.println(verifyKey);
        System.out.println(captcha);
        if (captcha == null) {
            return 1002;
        }
        if (!code.equalsIgnoreCase(captcha)) {
            return 1003;
        }
        redisUtils.delete(verifyKey);
        return 1001;
    }
}

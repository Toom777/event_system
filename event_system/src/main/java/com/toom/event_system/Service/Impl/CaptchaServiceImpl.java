package com.toom.event_system.Service.Impl;

import com.google.code.kaptcha.Producer;
import com.toom.event_system.Entity.CaptchaEntity;
import com.toom.event_system.Service.CaptchaService;
import org.springframework.stereotype.Service;
import sun.misc.BASE64Encoder;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import java.io.ByteArrayOutputStream;
import java.util.UUID;

@Service
public class CaptchaServiceImpl implements CaptchaService {

    @Resource(name = "captchaProducer")
    private Producer captchaProducer;

    @Resource(name = "captchaProducerMath")
    private Producer captchaProducerMath;

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
}

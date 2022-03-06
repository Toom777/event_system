package com.toom.event_system.Config;

import com.google.code.kaptcha.text.impl.DefaultTextCreator;
import com.toom.event_system.Common.KaptchaUtil;

import java.security.SecureRandom;
import java.util.Map;
import java.util.Random;

/**
 *  验证码文本生成器
 */
public class KaptchaTextCreator extends DefaultTextCreator {
    @Override
    public String getText(){
        Random random = new SecureRandom();

        //生成两个随机数，随机数范围：[0,10)，并返回结果
        Map<String, String> result = KaptchaUtil.mathTextCreator(random.nextInt(10), random.nextInt(10));
        return result.get("resultString");

    }
}

package com.toom.event_system.Controller;


import cn.hutool.core.io.FastByteArrayOutputStream;
import com.toom.event_system.Common.Base64;
import com.toom.event_system.Common.RedisUtils;
import com.toom.event_system.Common.Result;
import com.toom.event_system.Entity.CaptchaEntity;
import com.toom.event_system.Service.CaptchaService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;


import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.TimeUnit;


@RestController
public class CaptchaController {
    @Autowired
    private CaptchaService captchaService;

    @Autowired
    private RedisUtils redisUtils;

    @Resource
    protected HttpServletRequest res;
    @Resource
    protected HttpServletResponse resp;

    @GetMapping("/captchaImage")
    public Result getCaptchaData() {

        Result result = Result.success();
        // 生成验证码实体
        CaptchaEntity captchaEntity = captchaService.createCaptcha();

        if (captchaEntity != null) {

            // 写入唯一Token
            captchaEntity.setToken(UUID.randomUUID().toString());
            // 将验证码保存至redis
            redisUtils.set(captchaEntity.getToken(), captchaEntity.getCode(), 2, TimeUnit.MINUTES);
            FastByteArrayOutputStream os = new FastByteArrayOutputStream();
            try{
                ImageIO.write(captchaEntity.getImage(), "jpg", os);
            }catch (IOException e){
                return Result.error(e.getMessage());
            }
            // 写入base64格式

            captchaEntity.setBase64(Base64.encode(os.toByteArray()));

            result.put("uuid", captchaEntity.getToken());
            result.put("img", captchaEntity.getBase64());

             //打印测试
            System.out.println("Token：" + captchaEntity.getToken() + "\t验证码：" + captchaEntity.getCode());

            // 无用信息设空
            captchaEntity.setText(null);
            captchaEntity.setCode(null);
            // 返回前端信息
            return result;
        } else {
            return null;
        }
    }




}

package com.toom.event_system.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.awt.image.BufferedImage;
import java.io.Serializable;

/**
 * 验证码实体类
 */
public class CaptchaEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 验证码的Token
     */
    private String token;

    /**
     * 验证码的字符。返回的JSON，禁止返回给前端。
     */
    @JsonIgnore
    private String text;

    /**
     * 验证码的验证字符。比如算式的结果等。
     */
    @JsonIgnore
    private String code;

    /**
     * 验证码缓冲图像
     */
    @JsonIgnore
    private BufferedImage image;

    /**
     * 验证码图片的Base64字符串
     */
    private String base64;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public String getBase64() {
        return base64;
    }

    public void setBase64(String base64) {
        this.base64 = base64;
    }

    @Override
    public String toString() {
        return "CaptchaEntity{" +
                "token='" + token + '\'' +
                ", text='" + text + '\'' +
                ", code='" + code + '\'' +
                ", image=" + image +
                ", base64='" + base64 + '\'' +
                '}';
    }
}

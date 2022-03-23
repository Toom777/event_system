package com.toom.event_system.Service;


import java.util.Map;

/**
 * 登录服务接口
 * @author Toom
 */
public interface LoginService {

    /**
     * 登录校验
     * @param username
     * @param password
     * @return
     */
    Map<String, Object> login(String username, String password);
}

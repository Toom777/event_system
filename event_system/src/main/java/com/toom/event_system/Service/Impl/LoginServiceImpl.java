package com.toom.event_system.Service.Impl;


import cn.hutool.extra.servlet.ServletUtil;
import com.toom.event_system.Common.JwtToken;
import com.toom.event_system.Common.Result;
import com.toom.event_system.Common.Utils.IpUtils;
import com.toom.event_system.Common.Utils.JwtUtils;
import com.toom.event_system.Entity.User;
import com.toom.event_system.Service.*;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.Subject;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.mvc.condition.RequestConditionHolder;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


/**
 * 登录实现类
 * @author Toom
 */
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private UserService userService;

    @Autowired
    private MenuService menuService;

    @Autowired
    private RoleService roleService;


    /**
     * 登录校验
     * @param username
     * @param password
     * @return
     */
    @Override
    public Map<String, Object> login(String username, String password) {

        Map<String, Object> map = new HashMap<>();
        Subject subject = SecurityUtils.getSubject();
        String jwt = JwtUtils.createJWT(username, "back", "user", 1000*60*30);
        JwtToken jwtToken = new JwtToken(jwt, password);
        try {
            subject.login(jwtToken);
        }catch (UnknownAccountException e){
            Map<String, Object> errorMap = new HashMap<>();
            errorMap.put("401", "UnknownAccountException");
            return errorMap;
        }catch (IncorrectCredentialsException e){
            Map<String, Object> errorMap = new HashMap<>();
            errorMap.put("401", "IncorrectCredentialsException");
            return errorMap;
        }
        User backUser = userService.getUserByUsername(username);
        backUser.setPassword(null);
        //记录登录信息
        recordLoginInfo(backUser.getUserId());
        map.put("user", backUser);
        map.put("roles", roleService.getRoleByUsername(backUser.getUsername()));
        map.put("permissions", menuService.getPermissionByUsername(backUser.getUsername()));
        map.put("token", jwt);
        return map;
    }

    /**
     * 记录登录信息
     */
    public void recordLoginInfo(Long userId) {
        User user = new User();
        user.setUserId(userId);
        //user.setLoginIp(IpUtils.getIpAddr();
        user.setLoginDate(new Date());
        userService.updateUser(user);
    }
}

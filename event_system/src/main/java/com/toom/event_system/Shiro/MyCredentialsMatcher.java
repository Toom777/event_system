package com.toom.event_system.Shiro;

import com.toom.event_system.Common.JwtToken;
import com.toom.event_system.Entity.User;
import com.toom.event_system.Service.UserService;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 密码验证器
 */
@Component
public class MyCredentialsMatcher extends SimpleCredentialsMatcher {

    @Autowired
    private UserService userService;


    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        JwtToken jwtToken = (JwtToken)token;
        //token只传了账号信息没有密码信息，因此只要有token就直接通过
        if (jwtToken.getPassword() == null){
            return true;
        }
        //获取token内的密码
        String inPassword = new String(jwtToken.getPassword());
        String username = String.valueOf(info.getPrincipals());
        //获取用户数据库内的密码
        String dbPassword = (String) info.getCredentials();
        User user = userService.getUserByUsername(username);
        /*此处密码可以加盐值*/
        return this.equals(inPassword, dbPassword);
    }
}

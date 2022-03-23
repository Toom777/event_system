package com.toom.event_system.Common;


import com.toom.event_system.Common.Utils.JwtUtils;
import com.toom.event_system.Entity.User;
import com.toom.event_system.Service.MenuService;
import com.toom.event_system.Service.RoleService;
import com.toom.event_system.Service.UserService;
import io.jsonwebtoken.Claims;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * 认证授权实现类
 * @author Toom
 */
@Component
public class MyRealm extends AuthorizingRealm {
    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private MenuService menuService;


    /**
     * 标识MyRealm专门用来验证JwtToken，判断是否用的JwtToken类
     * @param token
     * @return
     */
    @Override
    public boolean supports(AuthenticationToken token){
        return token instanceof JwtToken;
    }


    /**
     * 授权验证，获取授权信息(角色和权限)
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

        /*String username = (String) principals.getPrimaryPrincipal();*/

        // 获取SimpleAuthenticationInfo(username, user.getPassword(), getName()) 的参数
        String username = (String)principals.iterator().next();

        // 返回权限
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.setRoles(roleService.getRoleByUsername(username));
        info.setStringPermissions(menuService.getPermissionByUsername(username));
        return info;
    }

    /**
     * 登录认证，获取身份信息
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {


        JwtToken jwtToken = (JwtToken)token;
        String jwt = (String) jwtToken.getPrincipal();
        Claims claims = JwtUtils.parseJWT(jwt);

        //获取用户名
        String username = claims.getId();
        User user = userService.getUserByUsername(username);
        if (user == null){
            //未找到账号
            throw new UnknownAccountException();
        }
        return new SimpleAuthenticationInfo(username, user.getPassword(), getName());
    }
}

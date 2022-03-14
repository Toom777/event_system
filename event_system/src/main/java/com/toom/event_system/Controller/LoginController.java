package com.toom.event_system.Controller;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.toom.event_system.Common.*;
import com.toom.event_system.Entity.User;
import com.toom.event_system.Service.MenuService;
import com.toom.event_system.Service.RoleService;
import com.toom.event_system.Service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 登录控制层
 * @author Toom
 */
@Api(value = "登录Controller", tags = {"登录操作接口"})
@RestController
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private MenuService menuService;

    @Autowired
    private RoleService roleService;
    /**
     * 登录
     */
    @PostMapping("/login")
    @ApiOperation("登录")
    public Result login(@RequestBody User user){
        if (user.getPassword() == null || user.getUsername() == null){
            return Result.error(500, "用户名和密码错误");
        }

        Subject subject = SecurityUtils.getSubject();
        String jwt = JwtUtils.createJWT(user.getUsername(), "back", "user", 1000*60*30);
        JwtToken jwtToken = new JwtToken(jwt, user.getPassword());
        try {
            subject.login(jwtToken);
        }catch (UnknownAccountException e){
            return Result.error(401, "账号不存在！");
        }catch (IncorrectCredentialsException e){
            return Result.error(401, "密码错误！");
        }
        User backUser = userService.getUserByUsername(user.getUsername());
        backUser.setPassword(null);
        Map<String, Object> map = new HashMap<>();
        map.put("user", backUser);
        map.put("token", jwt);
        return Result.success("登录成功！", map);
    }

    @GetMapping("/getInfo")
    public Result getInfo(){
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        Set<String> roles = menuService.getPermissionByUsername(user.getUsername());
        Set<String> permissions = roleService.getRoleByUsername(user.getUsername());
        Result result = Result.success();
        result.put("user", user);
        result.put("roles", roles);
        result.put("permissions", permissions);
        return result;
    }

    /**
     * 获取用户菜单
     * @return
     */

    /**
     * TODO 后续转为在SecurityUtils中获取用户信息
     */
    @GetMapping("/getRouters")
    public Result getRouters(String username){
        if (StringUtils.isEmpty(username)){
            return Result.error(401, "未登录，请先登录！");
        }
        List<Map<String, Object>> list = menuService.getMenuList(username);
        if (list == null){
            return Result.error(500, "获取失败");
        }else {
            return Result.success("获取成功", list);
        }
    }
}

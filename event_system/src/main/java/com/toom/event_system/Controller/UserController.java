package com.toom.event_system.Controller;


import com.toom.event_system.Common.Result;
import com.toom.event_system.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 用户管理控制层
 * @author Toom
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/find")
    public Result find() {
        return Result.success(userService.list());
    }

}

package com.toom.event_system.Controller;

import com.toom.event_system.Common.LoginBody;
import com.toom.event_system.Common.Result;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    /**
     * 登录
     */
    @PostMapping("/login")
    public Result login(@RequestBody LoginBody loginBody){
        Result result = Result.success();


        return result;
    }
}

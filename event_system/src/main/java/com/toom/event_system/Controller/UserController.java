package com.toom.event_system.Controller;


import com.toom.event_system.Common.Result;
import com.toom.event_system.Entity.User;
import com.toom.event_system.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * 用户管理控制层
 * @author Toom
 */
@RestController
@RequestMapping("/user")
public class UserController extends BaseController{

    @Autowired
    private UserService userService;

    @RequestMapping("/find")
    public Result find() {
        return Result.success(userService.list());
    }


    /**
     * 获取所有用户
     */
    @GetMapping("/selectAll")
    public Result selectAll(){
        return Result.success(userService.selectUserAll());
    }


    /**
     * 通过id查用户
     */
    @RequestMapping("/selectById/{userId}")
    public Result selectById(@PathVariable Long userId) {
        return Result.success(userService.selectUserById(userId));
    }


    /**
     * 新增用户
     * @param user
     * @return
     */
    @PostMapping("/insert")
    public Result insertUser(@RequestBody User user) {
        user.setCreateBy("admin");
        return toAjax(userService.insertUser(user));
    }

    /**
     * 修改用户信息
     */
    @RequestMapping("/update")
    public Result updateUser(@RequestBody User user){
        user.setUpdateBy("admin");
        return toAjax(userService.updateUser(user));
    }

    /**
     * 删除用户
     */
    @DeleteMapping("/del/{userIds}")
    public Result removeUser(@PathVariable Long[] userIds){
        return toAjax(userService.deleteUserByIds(userIds));
    }
}

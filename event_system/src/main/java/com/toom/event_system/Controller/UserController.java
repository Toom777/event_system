package com.toom.event_system.Controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.toom.event_system.Common.Result;
import com.toom.event_system.Entity.PageInfo;
import com.toom.event_system.Entity.User;
import com.toom.event_system.Service.UserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * 用户管理控制层
 * @author Toom
 */
@Api(value = "用户Controller", tags = {"用户操作接口"})
@RestController
@RequestMapping("/user")
public class UserController extends BaseController{

    @Autowired
    private UserService userService;

    /**
     * 测试
     * @return
     */
    @RequestMapping("/find")
    public Result find() {
        return Result.success();
    }

    /**
     * 查找账号，用于登录验证
     */
    @RequestMapping("/findUser/{username}")
    public Result findUser(@PathVariable String username){
        return Result.success(userService.getUserByUsername(username));
    }

    /**
     * 获取所有用户
     */
    @GetMapping("/selectAll")
    public Result selectAll(){
        return Result.success(userService.selectUserAll());
    }

    /**
     * 获取用户列表（实施分页）
     */
    @GetMapping("/list")
    public PageInfo getUserList(@RequestBody @RequestParam("pageCurrent") Integer pageCurrent,
                            @RequestBody @RequestParam("pageSize") Integer pageSize){
        Page<User> page = new Page<>(pageCurrent, pageSize);
        userService.selectUserPage(page);

        PageInfo userInfo = new PageInfo();
        userInfo.setPageCurrent(pageCurrent);
        userInfo.setPageSize(pageSize);
        userInfo.setTotal(page.getTotal());
        userInfo.setRows(page.getRecords());

        return userInfo;
    }

    /**
     * 条件分页查询
     * @param pageCurrent
     * @param pageSize
     * @param communityId
     * @param username
     * @param name
     * @return
     */
    @GetMapping("/searchList")
    public PageInfo getSearchList(@RequestParam("pageCurrent") Integer pageCurrent,
                                  @RequestParam("pageSize") Integer pageSize,
                                  @RequestParam("communityId") Long communityId,
                                  @RequestParam("username") String username,
                                  @RequestParam("name") String name){
        Page<User> page = new Page<>(pageCurrent, pageSize);
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        if (!"".equals(username)){
            wrapper.like("username", username);
        }
        if (!"".equals(name)){
            wrapper.like("name", name);
        }
        if (communityId != null){
            wrapper.eq("community_id", communityId);
        }
        userService.searchUserPage(page, wrapper);
        PageInfo info = new PageInfo();
        info.setPageCurrent(pageCurrent);
        info.setPageSize(pageSize);
        info.setTotal(page.getTotal());
        info.setRows(page.getRecords());
        return info;
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

package com.toom.event_system.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.toom.event_system.Entity.PageInfo;
import com.toom.event_system.Entity.User;
import com.toom.event_system.Entity.User;

import java.util.List;


public interface UserService extends IService<User> {
    /**
     * 获取所有用户
     */
    public List<User> selectUserAll();


    /**
     * 分页查询
     */
    public Page<User> selectUserPage(Page<User> page);

    /**
     * 条件分页查询
     */
    public Page<User> searchUserPage(Page<User> page, QueryWrapper<User> wrapper);

    /**
     *  通过ID查找用户
     */
    public User selectUserById(Long userId);

    /**
     * 个人信息查看
     * @param userId
     * @return
     */
    public User selectUser(Long userId);

    /**
     * 新增用户
     * @param user
     * @return
     */
    public Boolean insertUser(User user);

    /**
     * 修改用户信息
     */
    public Boolean updateUser(User user);


    /**
     * 删除用户
     */
    public Boolean deleteUserById(Long userId);

    /**
     * 批量删除用户
     * @param userIds
     * @return
     */
    public Boolean deleteUserByIds(Long[]userIds);

    /**
     * 查找账号
     * @param username
     * @return
     */
    public User getUserByUsername(String username);


    /**
     * 统计用户数量
     * @return
     */
    public Long countUser();

    /**
     * 统计服务时长
     */
    public Long countHours();
}

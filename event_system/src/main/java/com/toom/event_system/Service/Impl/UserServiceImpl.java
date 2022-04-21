package com.toom.event_system.Service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.toom.event_system.Entity.User;
import com.toom.event_system.Entity.UserRole;
import com.toom.event_system.Mapper.UserMapper;
import com.toom.event_system.Mapper.UserRoleMapper;
import com.toom.event_system.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Toom
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    /**
     * 查找所有用户
     * @return
     */
    @Override
    public List<User> selectUserAll() {
        return userMapper.selectList(null);
    }

    /**
     * 分页查询
     */
    @Override
    public Page<User> selectUserPage(Page<User> page) {
        return userMapper.selectPage(page, null);
    }

    /**
     * 条件分页查询
     * @param page
     * @param wrapper
     * @return
     */
    @Override
    public Page<User> searchUserPage(Page<User> page, QueryWrapper<User> wrapper) {
        return userMapper.selectPage(page, wrapper);
    }

    /**
     * 用过ID查找用户
     * @param userId
     * @return
     */
    @Override
    public User selectUserById(Long userId) {
        return userMapper.selectById(userId);
    }

    /**
     * 个人信息查看
     * @param userId
     * @return
     */
    @Override
    public User selectUser(Long userId) {
        User user = userMapper.selectById(userId);
        /** 星级评定 根据时长定*/
        if (user.getHours() < 20L) {
            user.setSating(1L);
        } else if (user.getHours() < 60L) {
            user.setSating(2L);
        } else if (user.getHours() < 100L) {
            user.setSating(3L);
        } else if (user.getHours() < 200L) {
            user.setSating(4L);
        } else {
            user.setSating(5L);
        }
        userMapper.updateById(user);
        System.out.println(user.getHours());
        System.out.println(user.getSating());
        return user;
    }

    /**
     * 新增用户
     * @param user
     * @return
     */
    @Override
    public Boolean insertUser(User user) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("username", user.getUsername());
        if (userMapper.selectOne(wrapper) == null){
            return userMapper.insert(user) > 0 ? true : false;
        } else {
            return false;
        }
    }

    /**
     * 修改用户
     * @param user
     * @return
     */
    @Override
    public Boolean updateUser(User user) {
        user.setUpdateTime(new Date());
        return userMapper.updateById(user) > 0 ? true : false;
    }

    /**
     * 删除用户
     * @param userId
     * @return
     */
    @Override
    public Boolean deleteUserById(Long userId) {
        return userMapper.deleteById(userId) > 0 ? true : false;
    }

    /**
     * 批量删除用户
     * @param userIds
     * @return
     */
    @Override
    public Boolean deleteUserByIds(Long[] userIds) {
        List<Long> list = new ArrayList<>();
        for (long id : userIds) {
            list.add(id);
        }
        return userMapper.deleteBatchIds(list) > 0 ? true : false;
    }

    /**
     * 查找账号
     * @param username
     * @return
     */
    @Override
    public User getUserByUsername(String username) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("username", username);
        User user = userMapper.selectOne(wrapper);
        return user;
    }

    /**
     * 用户数量统计
     * @return
     */
    @Override
    public Long countUser() {
        return userMapper.selectCount(null);
    }

    /**
     * 统计服务时长
     * @return
     */
    @Override
    public Long countHours() {
        return userMapper.hoursCount();
    }
}

package com.toom.event_system.Service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.toom.event_system.Entity.User;
import com.toom.event_system.Mapper.UserMapper;
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
     * 新增用户
     * @param user
     * @return
     */
    @Override
    public Boolean insertUser(User user) {
        return userMapper.insert(user) > 0 ? true : false;
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
}

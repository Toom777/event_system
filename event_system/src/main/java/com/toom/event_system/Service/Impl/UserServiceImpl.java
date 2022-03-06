package com.toom.event_system.Service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.toom.event_system.Entity.User;
import com.toom.event_system.Mapper.UserMapper;
import com.toom.event_system.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
}

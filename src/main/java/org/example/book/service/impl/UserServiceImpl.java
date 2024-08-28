package org.example.book.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import org.example.book.dao.entity.User;
import org.example.book.dao.mapper.UserMapper;
import org.example.book.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2024/03/10
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public  User getUserByName(User user) {

        QueryWrapper<User> wrapper = new QueryWrapper<>();
        // 添加多个条件
        wrapper.eq("username", user.getUsername());

        return userMapper.selectOne(wrapper);
    }

    @Override
    public void add(User user) {
        userMapper.insert(user);
    }

    @Override
    public User getUser(User user) {

        QueryWrapper<User> wrapper = new QueryWrapper<>();

        wrapper.eq("username", user.getUsername());
        wrapper.eq("password", user.getPassword());

        return userMapper.selectOne(wrapper);
    }
}

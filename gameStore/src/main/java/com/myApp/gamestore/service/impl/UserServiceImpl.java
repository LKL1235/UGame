package com.myApp.gamestore.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.myApp.gamestore.entity.User;
import com.myApp.gamestore.service.UserService;
import com.myApp.gamestore.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
* @author 25445
* @description 针对表【user】的数据库操作Service实现
* @createDate 2022-11-25 14:13:07
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

    private UserMapper userMapper;
    @Autowired
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public boolean createLibrary(String name) {
        return userMapper.createLibrary(name);
    }
}





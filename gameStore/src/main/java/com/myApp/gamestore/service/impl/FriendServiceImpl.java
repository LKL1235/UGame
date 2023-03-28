package com.myApp.gamestore.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.myApp.gamestore.entity.Friend;
import com.myApp.gamestore.service.FriendService;
import com.myApp.gamestore.mapper.FriendMapper;
import org.springframework.stereotype.Service;

/**
* @author 25445
* @description 针对表【friend】的数据库操作Service实现
* @createDate 2023-03-25 10:40:01
*/
@Service
public class FriendServiceImpl extends ServiceImpl<FriendMapper, Friend>
    implements FriendService{

}





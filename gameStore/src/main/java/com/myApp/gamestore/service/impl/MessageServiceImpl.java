package com.myApp.gamestore.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.myApp.gamestore.entity.Message;
import com.myApp.gamestore.service.MessageService;
import com.myApp.gamestore.mapper.MessageMapper;
import org.springframework.stereotype.Service;

/**
* @author 25445
* @description 针对表【message】的数据库操作Service实现
* @createDate 2023-03-25 10:54:13
*/
@Service
public class MessageServiceImpl extends ServiceImpl<MessageMapper, Message>
    implements MessageService{

}





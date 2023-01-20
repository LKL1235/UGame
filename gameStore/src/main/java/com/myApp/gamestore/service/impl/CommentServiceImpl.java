package com.myApp.gamestore.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.myApp.gamestore.entity.Comment;
import com.myApp.gamestore.service.CommentService;
import com.myApp.gamestore.mapper.CommentMapper;
import org.springframework.stereotype.Service;

/**
* @author 25445
* @description 针对表【comment】的数据库操作Service实现
* @createDate 2022-12-09 18:05:43
*/
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment>
    implements CommentService{

}





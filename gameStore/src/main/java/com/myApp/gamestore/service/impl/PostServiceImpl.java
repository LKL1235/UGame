package com.myApp.gamestore.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.myApp.gamestore.DTO.PostsListDTO;
import com.myApp.gamestore.entity.Post;
import com.myApp.gamestore.service.PostService;
import com.myApp.gamestore.mapper.PostMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author 25445
* @description 针对表【post】的数据库操作Service实现
* @createDate 2023-03-13 23:11:29
*/
@Service
public class PostServiceImpl extends ServiceImpl<PostMapper, Post>
    implements PostService{

    @Autowired
    private PostMapper postMapper;
    @Override
    public List<PostsListDTO> getPostsList(Integer boardId) {
        return postMapper.getPostsList(boardId);
    }
}





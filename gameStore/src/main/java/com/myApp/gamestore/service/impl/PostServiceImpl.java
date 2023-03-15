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
* @createDate 2023-03-15 22:54:49
*/
@Service
public class PostServiceImpl extends ServiceImpl<PostMapper, Post>
    implements PostService{

    @Autowired
    private PostMapper postMapper;
    @Override
    public List<PostsListDTO> getPostsListBoardPage(Integer boardId, Integer page) {
        return postMapper.getPostsListBoardPage(boardId,(page-1)*10);
    }

    @Override
    public List<PostsListDTO> getPostsListPage(Integer page) {
        return postMapper.getPostsListPage((page-1)*10);
    }
}





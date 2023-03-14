package com.myApp.gamestore.service;

import com.myApp.gamestore.DTO.PostsListDTO;
import com.myApp.gamestore.entity.Post;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author 25445
* @description 针对表【post】的数据库操作Service
* @createDate 2023-03-13 23:11:29
*/
public interface PostService extends IService<Post> {

    List<PostsListDTO> getPostsList(Integer boardId);
}

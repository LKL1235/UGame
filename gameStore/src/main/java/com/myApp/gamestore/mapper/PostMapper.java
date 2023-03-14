package com.myApp.gamestore.mapper;

import com.myApp.gamestore.DTO.PostsListDTO;
import com.myApp.gamestore.entity.Post;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
* @author 25445
* @description 针对表【post】的数据库操作Mapper
* @createDate 2023-03-13 23:11:29
* @Entity com.myApp.gamestore.entity.Post
*/
@Mapper
public interface PostMapper extends BaseMapper<Post> {

    List<PostsListDTO> getPostsList(Integer boardId);
}





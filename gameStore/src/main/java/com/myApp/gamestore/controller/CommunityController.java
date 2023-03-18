package com.myApp.gamestore.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.myApp.gamestore.DTO.PostsListDTO;
import com.myApp.gamestore.entity.Board;
import com.myApp.gamestore.entity.Post;
import com.myApp.gamestore.service.BoardService;
import com.myApp.gamestore.service.PostService;
import com.myApp.gamestore.utils.ResultCode;
import com.myApp.gamestore.utils.myResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @description:
 * @author:25445
 * @date:2023/3/13 22:57
 **/
@RestController
public class CommunityController {

    @Autowired
    private PostService postService;
    @Autowired
    private BoardService boardService;

    // /getPosts?boardId=
    @RequestMapping("/getPosts")
    public myResult getPosts(@RequestParam(required = false) Integer boardId, @RequestParam(required = false, defaultValue = "1") Integer page) {
        if (boardId != null) {
            // QueryWrapper<Post> queryWrapper = new QueryWrapper<Post>();
            // queryWrapper.eq("board_id",boardId);
            List<PostsListDTO> list = postService.getPostsListBoardPage(boardId, page);
            // List<Post> list = postService.list(queryWrapper);
            return new myResult(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMsg(), list);
        }
        List<PostsListDTO> list = postService.getPostsListPage(page);
        return new myResult(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMsg(), list);
    }

    @RequestMapping("/getBoards")
    public myResult getBoards(@RequestParam(required = false, defaultValue = "1") Integer page){
        List<Board> list = boardService.listPage(page);
        return new myResult(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMsg(),list);
    }
}

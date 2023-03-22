package com.myApp.gamestore.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.myApp.gamestore.DTO.PostDTO;
import com.myApp.gamestore.DTO.PostsListDTO;
import com.myApp.gamestore.entity.Board;
import com.myApp.gamestore.entity.Post;
import com.myApp.gamestore.entity.Reply;
import com.myApp.gamestore.service.BoardService;
import com.myApp.gamestore.service.PostService;
import com.myApp.gamestore.service.ReplyService;
import com.myApp.gamestore.utils.ResultCode;
import com.myApp.gamestore.utils.myResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
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
    @Autowired
    private ReplyService replyService;


    @RequestMapping("/addPost")
    public myResult addPost(@RequestBody Post post) {

        return new myResult(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMsg());
    }

    // /getPosts?boardId=
    @RequestMapping("/getPosts")
    public myResult getPosts(@RequestParam(required = false,defaultValue = "") String name,@RequestParam(required = false) Integer boardId, @RequestParam(required = false, defaultValue = "1") Integer page) {
        if (boardId != null) {
            List<PostsListDTO> list = postService.getPostsListBoardPage(boardId, page);
            return new myResult(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMsg(), list);
        }
        List<PostsListDTO> list = postService.getPostsListLikePage(name, page);
        return new myResult(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMsg(), list);
    }

    @RequestMapping("/getPostsLike")
    public myResult getPostsLike(@RequestParam(required = false,defaultValue = "") String name, @RequestParam(required = false, defaultValue = "1") Integer page) {

            List<PostsListDTO> list = postService.getPostsListLikePage(name, page);

            return new myResult(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMsg(), list);
    }

    @RequestMapping("getPostInfo")
    public myResult getPostInfo(@RequestParam(required = true)Integer postId){
        PostsListDTO postInfo = postService.getPostInfo(postId);
        return new myResult(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMsg(),postInfo);
    }

    @RequestMapping("/addBoard")
    public myResult addBoard(@RequestBody Board board) {

        return new myResult(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMsg());
    }
    @RequestMapping("/getBoards")
    public myResult getBoards(@RequestParam(required = false, defaultValue = "1") Integer page){
        List<Board> list = boardService.listPage(page);
        return new myResult(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMsg(),list);
    }
    @RequestMapping("/getBoardsLike")
    public myResult getBoardsLike(@RequestParam(required = false, defaultValue = "") String name,@RequestParam(required = false, defaultValue = "1") Integer page){
        QueryWrapper<Board> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("board_name",name);
        // queryWrapper.last();
        List<Board> list = boardService.list(queryWrapper);
        return new myResult(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMsg(),list);
    }

    @RequestMapping("/getReplies")
    public myResult getReplies(@RequestParam(required = false, defaultValue = "") Integer postId){
        QueryWrapper<Reply> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("created_time");
        queryWrapper.eq("post_id",postId);
        List<Reply> list = replyService.list(queryWrapper);
        return new myResult(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMsg(),list);
    }

}

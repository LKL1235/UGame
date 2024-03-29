package com.myApp.gamestore.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
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

import java.util.Date;
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
        boolean result = postService.save(post);
        return new myResult(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMsg(),result);
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
        boardService.save(board);
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

    @RequestMapping("addReply")
    public myResult addReply(@RequestBody Reply reply){
        boolean result = replyService.save(reply);
        if (result) {
            UpdateWrapper<Post> updateWrapper = new UpdateWrapper<>();
            updateWrapper.eq("post_id",reply.getPostId());
            updateWrapper.set("last_reply_time",reply.getCreatedTime());
            postService.update(updateWrapper);
            return new myResult(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMsg());
        }
        return new myResult(ResultCode.FAILURE.getCode(), ResultCode.FAILURE.getMsg());
    }

    @RequestMapping("/getReplies")
    public myResult getReplies(@RequestParam(required = false, defaultValue = "") Integer postId,@RequestParam(required = false, defaultValue = "1") Integer page){
        QueryWrapper<Reply> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("created_time");
        queryWrapper.eq("post_id",postId);
        queryWrapper.last("limit "+(page-1)*10+",10");
        List<Reply> list = replyService.list(queryWrapper);
        return new myResult(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMsg(),list);
    }

    @RequestMapping("/post/all")
    public myResult all(){
        List<Post> list = postService.list();
        return new myResult(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMsg(),list);
    }

    @RequestMapping("/post/delete")
    public myResult delete(@RequestParam Integer postId){
        QueryWrapper<Post> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("post_id",postId);
        postService.remove(queryWrapper);
        return new myResult(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMsg());
    }

    @RequestMapping("/board/delete")
    public myResult boardDelete(@RequestParam Integer boardId){
        QueryWrapper<Board> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("board_id",boardId);
        boardService.remove(queryWrapper);
        return new myResult(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMsg());
    }
}

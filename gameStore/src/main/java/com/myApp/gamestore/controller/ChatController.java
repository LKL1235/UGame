package com.myApp.gamestore.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.myApp.gamestore.entity.Friend;
import com.myApp.gamestore.entity.FriendApply;
import com.myApp.gamestore.service.FriendApplyService;
import com.myApp.gamestore.service.FriendService;
import com.myApp.gamestore.utils.ResultCode;
import com.myApp.gamestore.utils.myResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

/**
 * @description:
 * @author:25445
 * @date:2023/3/28 11:07
 **/
@RestController
public class ChatController {

    @Autowired
    private FriendApplyService friendApplyService;
    @Autowired
    private FriendService friendService;

    @RequestMapping("/addFriendApply")
    public myResult addFriendApply(@RequestParam(required = true,name = "fromUser") String fromUser, @RequestParam(required = true , name="toUser") String toUser, @RequestParam(name="timestamp")Date time){
        QueryWrapper<FriendApply> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("from_user",fromUser);
        queryWrapper.eq("to_user",toUser);
        FriendApply friendApply=friendApplyService.getOne(queryWrapper);
        if (friendApply.getId()==null){
            return new myResult(ResultCode.FAILURE.getCode(), ResultCode.FAILURE.getMsg());
        }
        boolean save = friendApplyService.save(new FriendApply(null,fromUser,toUser,time,null));
        return new myResult(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMsg(),save);
    }


    @RequestMapping("/updateFriendApply")
    public myResult updateFriendApply(@RequestParam(required = true,name = "fromUser") String fromUser, @RequestParam(required = true , name="toUser") String toUser){
        UpdateWrapper<FriendApply> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("from_user",fromUser);
        updateWrapper.eq("to_user",toUser);
        updateWrapper.set("apply",true);
        boolean result = friendApplyService.update(updateWrapper);
        if (result){
            friendService.save(new Friend(null,fromUser,toUser));
            friendService.save(new Friend(null,toUser,fromUser));
        }
        return new myResult(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMsg(),result);
    }

    @RequestMapping("/rejectFriendApply")
    public myResult rejectFriendApply(@RequestParam(required = true,name = "fromUser") String fromUser, @RequestParam(required = true , name="toUser") String toUser){
        UpdateWrapper<FriendApply> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("from_user",fromUser);
        updateWrapper.eq("to_user",toUser);
        updateWrapper.set("apply",false);
        boolean result = friendApplyService.update(updateWrapper);
        return new myResult(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMsg(),result);
    }

    @RequestMapping("/getFriendApply")
    public myResult getFriendApply(@RequestParam(required = true,name = "userName") String userName){
        QueryWrapper<FriendApply> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("to_User",userName);
        queryWrapper.eq("apply",null);
        List<FriendApply> friendApplyList = friendApplyService.list(queryWrapper);
        return new myResult(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMsg(),friendApplyList);
    }

    @RequestMapping("/getFriend")
    public myResult getFriend(@RequestParam(required = true,name = "userName") String userName){
        QueryWrapper<Friend> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_name",userName);
        List list =friendService.list(queryWrapper);
        return new myResult(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMsg(),list);
    }
}

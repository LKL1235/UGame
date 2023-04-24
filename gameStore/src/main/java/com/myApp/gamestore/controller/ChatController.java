package com.myApp.gamestore.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.myApp.gamestore.DTO.FriendApplyDTO;
import com.myApp.gamestore.DTO.FriendDTO;
import com.myApp.gamestore.entity.Friend;
import com.myApp.gamestore.entity.FriendApply;
import com.myApp.gamestore.entity.Message;
import com.myApp.gamestore.entity.User;
import com.myApp.gamestore.service.FriendApplyService;
import com.myApp.gamestore.service.FriendService;
import com.myApp.gamestore.service.MessageService;
import com.myApp.gamestore.service.UserService;
import com.myApp.gamestore.utils.ResultCode;
import com.myApp.gamestore.utils.myResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @description:
 * @author:25445
 * @date:2023/3/28 11:07
 **/
@RestController
public class ChatController {

    private static final Logger LOG = LoggerFactory.getLogger(ChatController.class);
    @Autowired
    private FriendApplyService friendApplyService;
    @Autowired
    private FriendService friendService;
    @Autowired
    private UserService userService;

    @Autowired
    private MessageService messageService;

    @RequestMapping("/addFriendApply")
    public myResult addFriendApply(@RequestParam(required = true,name = "fromUser") String fromUser, @RequestParam(required = true , name="toUser") String toUser, @RequestParam(name="timestamp",required = false)Date time){
        QueryWrapper<User> userQuery = new QueryWrapper<>();
        userQuery.eq("name",toUser);
        User user=userService.getOne(userQuery);
        if (user==null){
            return new myResult(ResultCode.FAILURE.getCode(), ResultCode.FAILURE.getMsg());
        }
        QueryWrapper<Friend> friendQuery = new QueryWrapper<>();
        friendQuery.eq("user_name",fromUser);
        List<Friend> list = friendService.list(friendQuery);
        AtomicInteger repeated = new AtomicInteger();
        list.forEach(item->{
            String friendName=item.getFriendName();
            if(Objects.equals(friendName, toUser)){
                repeated.set(1);
            }
        });
        if (repeated.get()==1){
            return new myResult(ResultCode.FAILURE.getCode(), "已是好友");
        }

        QueryWrapper<FriendApply> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("from_user",fromUser);
        queryWrapper.eq("to_user",toUser);
        FriendApply friendApply=friendApplyService.getOne(queryWrapper);
        if (friendApply!=null){
            return new myResult(ResultCode.FAILURE.getCode(), "重复申请");
        }

        boolean save = friendApplyService.save(new FriendApply(null,fromUser,toUser,time,null));
        return new myResult(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMsg(),save);
    }

    @RequestMapping("/deleteFriend")
    public myResult deleteFriend(@RequestParam(required = true,name = "fromUser") String fromUser, @RequestParam(required = true , name="toUser") String toUser){
        QueryWrapper<Friend> queryWrapper = new QueryWrapper<Friend>();
        queryWrapper.eq("user_name",fromUser);
        queryWrapper.eq("friend_name",toUser);
        Friend friend = friendService.getOne(queryWrapper);
        friendService.removeById(friend);

        QueryWrapper<Friend> queryWrapper2 = new QueryWrapper<Friend>();
        queryWrapper2.eq("user_name",toUser);
        queryWrapper2.eq("friend_name",fromUser);
        Friend friend2 = friendService.getOne(queryWrapper2);
        friendService.removeById(friend2);

        QueryWrapper<FriendApply> friendApplyQueryWrapper = new QueryWrapper<>();
        friendApplyQueryWrapper.eq("from_user",fromUser);
        friendApplyQueryWrapper.eq("to_user",toUser);
        friendApplyService.remove(friendApplyQueryWrapper);

        QueryWrapper<FriendApply> friendApplyQueryWrapper2 = new QueryWrapper<>();
        friendApplyQueryWrapper2.eq("to_user",fromUser);
        friendApplyQueryWrapper2.eq("from_user",toUser);
        friendApplyService.remove(friendApplyQueryWrapper2);


        return new myResult(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMsg());
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
        queryWrapper.eq("to_user",userName);
        queryWrapper.isNull("apply");
        List<FriendApply> friendApplyList = friendApplyService.list(queryWrapper);
        List<FriendApplyDTO> friendApplyDTOList = new ArrayList<>();

        friendApplyList.forEach(item->{
            QueryWrapper<User> friendApplyDTOQueryWrapper = new QueryWrapper<>();
            friendApplyDTOQueryWrapper.eq("name",item.getFromUser());
            User user = userService.getOne(friendApplyDTOQueryWrapper);
            friendApplyDTOList.add(new FriendApplyDTO(user.getName(),user.getAvatar()));
        });
        return new myResult(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMsg(),friendApplyDTOList);
    }

    @RequestMapping("/getFriend")
    public myResult getFriend(@RequestParam(required = true,name = "userName") String userName){
        QueryWrapper<Friend> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_name",userName);
        List<Friend> list =friendService.list(queryWrapper);

        List<FriendDTO> friendDTOList = new ArrayList<>();
        list.forEach(item->{
            QueryWrapper<User> friendApplyDTOQueryWrapper = new QueryWrapper<>();
            friendApplyDTOQueryWrapper.eq("name",item.getFriendName());
            User user = userService.getOne(friendApplyDTOQueryWrapper);
            friendDTOList.add(new FriendDTO(user.getName(),user.getAvatar()));
        });
        return new myResult(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMsg(),friendDTOList);
    }

    @RequestMapping("/getChatHistory")
    public myResult getChatHistory(@RequestParam String userName,@RequestParam String friend){
        QueryWrapper<Message> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("from_user_name",userName);
        queryWrapper.eq("to_user_name",friend);
        queryWrapper.orderByAsc("time_stamp");
        List<Message> list1 = messageService.list(queryWrapper);

        QueryWrapper<Message> queryWrapper2 = new QueryWrapper<>();
        queryWrapper2.eq("from_user_name",friend);
        queryWrapper2.eq("to_user_name",userName);
        queryWrapper2.orderByAsc("time_stamp");
        List<Message> list2 = messageService.list(queryWrapper2);

        List<Message> list =  new ArrayList<>();
        list.addAll(list1);
        list.addAll(list2);
        list.sort(Comparator.comparing(Message::getId));
        return new myResult(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMsg(),list);
    }
}

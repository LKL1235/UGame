package com.myApp.gamestore.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.myApp.gamestore.DTO.GameShowDTO;
import com.myApp.gamestore.entity.Game;
import com.myApp.gamestore.service.GameService;
import com.myApp.gamestore.utils.ResultCode;
import com.myApp.gamestore.utils.myResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author:25445
 * @date:2022/12/9 18:10
 **/
@RestController
@RequestMapping("/game")
public class GameController {

    @Autowired
    private GameService gameService;

    @RequestMapping("/show")
    public myResult show(){
        List<Game> list=gameService.gameShow(5);
        List<GameShowDTO> gameShowDTOList=new ArrayList<>();
        for (int i=0;i<=list.size()-1;i++) {
            GameShowDTO gameShowDTO = new GameShowDTO();
            gameShowDTO.setGameId(list.get(i).getGameId());
            gameShowDTO.setGameName(list.get(i).getGameName());
            ArrayList<String> strings = new ArrayList<>();
            for (int j=0;j<=4;j++) {
                strings.add("http://139.9.32.27/src/ugame/images/" +list.get(i).getImages()+"/"+ j + ".jpg");
            }
            gameShowDTO.setImg(strings);
            List<String> tagStringList=new ArrayList<>();
            String[] tagString;
            tagString=list.get(i).getTags().split(",");
            for (int k=0;k<=tagString.length-1;k++) {
                tagStringList.add(tagString[k]);
            }
            gameShowDTO.setTags(tagStringList);
            gameShowDTO.setPrice(list.get(i).getPrice());
            gameShowDTOList.add(gameShowDTO);
        }
        return new myResult(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMsg(),gameShowDTOList);
    }
}

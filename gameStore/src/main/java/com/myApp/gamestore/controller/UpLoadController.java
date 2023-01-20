package com.myApp.gamestore.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.myApp.gamestore.DTO.UpLoadDTO;
import com.myApp.gamestore.entity.Developer;
import com.myApp.gamestore.entity.Game;
import com.myApp.gamestore.service.DeveloperService;
import com.myApp.gamestore.service.GameService;
import com.myApp.gamestore.utils.ResultCode;
import com.myApp.gamestore.utils.myResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @author:25445
 * @date:2023/1/12 20:29
 **/
@RestController
@RequestMapping("/game")
public class UpLoadController {
    @Autowired
    private DeveloperService developerService;
    @Autowired
    private GameService gameService;

    @RequestMapping("/upload")
    public myResult upload(@RequestBody UpLoadDTO upLoadDTO){
        QueryWrapper queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("name",upLoadDTO.getName());
        Developer developer=developerService.getOne(queryWrapper);
        String repository=developer.getRepository();
        Game game=new Game();
        game.setGameName(upLoadDTO.getGameName());
        game.setRepository(repository);
        game.setPrice(upLoadDTO.getPrice());
        game.setTags(upLoadDTO.getTags());
        game.setIntroduce(upLoadDTO.getIntroduce());
        game.setAbout(upLoadDTO.getAbout());
        gameService.save(game);
        return new myResult(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMsg());
    }
}

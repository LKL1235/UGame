package com.myApp.gamestore.controller;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.myApp.gamestore.DTO.GameInfoDTO;
import com.myApp.gamestore.DTO.GameShowDTO;
import com.myApp.gamestore.entity.Game;
import com.myApp.gamestore.service.GameService;
import com.myApp.gamestore.utils.ResultCode;
import com.myApp.gamestore.utils.myResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @description:
 * @author:25445
 * @date:2022/12/9 18:10
 **/
@RestController
@RequestMapping("/game")
public class GameController {
    private static final Logger LOG = LoggerFactory.getLogger(GameController.class);
    @Autowired
    private GameService gameService;

    private static final String BASE_FILE_PATH ="G:\\img\\";
    private static final String BASE_URL_PATH ="http://127.0.0.1:8889/";
    // @RequestMapping("/show")
    // public myResult show(){
    //     List<Game> list=gameService.gameShow(5);
    //     List<GameShowDTO> gameShowDTOList=new ArrayList<>();
    //     for (int i=0;i<=list.size()-1;i++) {
    //         GameShowDTO gameShowDTO = new GameShowDTO();
    //         gameShowDTO.setGameId(list.get(i).getGameId());
    //         gameShowDTO.setGameName(list.get(i).getGameName());
    //         ArrayList<String> strings = new ArrayList<>();
    //         for (int j=0;j<=4;j++) {
    //             strings.add("http://139.9.32.27/src/ugame/images/" +list.get(i).getImages()+"/"+ j + ".jpg");
    //         }
    //         gameShowDTO.setImg(strings);
    //         List<String> tagStringList=new ArrayList<>();
    //         String[] tagString;
    //         tagString=list.get(i).getTags().split(",");
    //         for (int k=0;k<=tagString.length-1;k++) {
    //             tagStringList.add(tagString[k]);
    //         }
    //         gameShowDTO.setTags(tagStringList);
    //         gameShowDTO.setPrice(list.get(i).getPrice());
    //         gameShowDTOList.add(gameShowDTO);
    //     }
    //     return new myResult(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMsg(),gameShowDTOList);
    // }

    @RequestMapping("/show/{offset}/{num}")
    public myResult show(@PathVariable int num,@PathVariable int offset){
        //num 取多少个 offset 从第几个开始取
        List<Game> list=gameService.gameShow(offset,num);
        List<GameShowDTO> gameShowDTOList=new ArrayList<>();
        list.forEach((item)->{
            GameShowDTO gameShowDTO = new GameShowDTO();
            gameShowDTO.setGameId(item.getGameId());
            gameShowDTO.setGameName(item.getGameName());
            ArrayList<String> strings = new ArrayList<>();
            String path = BASE_FILE_PATH+item.getImages();
            LOG.info("path={}",path);
            File file = new File(path);
            File[] fs = file.listFiles();
            LOG.info("fs={}", (Object) fs);
            if(fs!=null){
                for(File f:fs){
                    if(!f.isDirectory()) {
                        String s=f.getName();
                        String urlPath=BASE_URL_PATH+item.getGameName()+"/"+s;
                        strings.add(urlPath);
                    }
                }
                gameShowDTO.setImg(strings);
                List<String> tagStringList=new ArrayList<>();
                String[] tagString;
                tagString=item.getTags().split(",");
                Collections.addAll(tagStringList, tagString);
                gameShowDTO.setTags(tagStringList);
                gameShowDTO.setPrice(item.getPrice());
                gameShowDTOList.add(gameShowDTO);
            }
        });
        LOG.info("myResult={}",gameShowDTOList);
        return new myResult(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMsg(),gameShowDTOList);
    }
    @RequestMapping("/search")
    public myResult search(@RequestParam(defaultValue = "", name="gameName",required = false) String gameName,@RequestParam(defaultValue = "20",name="page",required = true) Integer page){
        List<Game> list=gameService.searchByName(gameName,page);
        List<GameShowDTO> gameShowDTOList=new ArrayList<>();
        list.forEach((item)->{
            GameShowDTO gameShowDTO = new GameShowDTO();
            gameShowDTO.setGameId(item.getGameId());
            gameShowDTO.setGameName(item.getGameName());
            ArrayList<String> strings = new ArrayList<>();
            String path = BASE_FILE_PATH+item.getImages();
            LOG.info("path={}",path);
            File file = new File(path);
            File[] fs = file.listFiles();
            LOG.info("fs={}", (Object) fs);
            if(fs!=null){
                for(File f:fs){
                    if(!f.isDirectory()) {
                        String s=f.getName();
                        String urlPath=BASE_URL_PATH+item.getGameName()+"/"+s;
                        strings.add(urlPath);
                    }
                }
                gameShowDTO.setImg(strings);
                List<String> tagStringList=new ArrayList<>();
                String[] tagString;
                tagString=item.getTags().split(",");
                Collections.addAll(tagStringList, tagString);
                gameShowDTO.setTags(tagStringList);
                gameShowDTO.setPrice(item.getPrice());
                gameShowDTOList.add(gameShowDTO);
            }
        });
        LOG.info("myResult={}",gameShowDTOList);
        return new myResult(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMsg(),gameShowDTOList);
    }
    @RequestMapping("/total")
    public myResult total(@RequestParam(name = "gameName",required = false) String gameName){
        if(gameName!=null) {
            if (!gameName.isEmpty()) {
                Integer total = gameService.total(gameName);
                return new myResult(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMsg(), total);
            } else {
                Long i = gameService.count();
                return new myResult(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMsg(), i);
            }
        }else{
            return new myResult(ResultCode.FAILURE.getCode(), ResultCode.UNKNOWN_ERROR.getMsg());
        }
    }
    @RequestMapping("/info/{gameId}")
    public myResult info(@PathVariable Integer gameId){
            Game game=gameService.getById(gameId);
            ArrayList<String> strings = new ArrayList<>();
            String path = BASE_FILE_PATH+game.getImages();
            LOG.info("path={}",path);
            File file = new File(path);
            File[] fs = file.listFiles();
            LOG.info("fs={}", (Object) fs);
            if(fs!=null){
                for(File f:fs){
                    if(!f.isDirectory()) {
                        String s=f.getName();
                        String urlPath=BASE_URL_PATH+game.getGameName()+"/"+s;
                        strings.add(urlPath);
                    }
                }
            }
            GameInfoDTO gameInfoDTO=new GameInfoDTO();
        BeanUtil.copyProperties(game,gameInfoDTO);
        gameInfoDTO.setImgs(strings);
        return new myResult(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMsg(),gameInfoDTO);
    }
}

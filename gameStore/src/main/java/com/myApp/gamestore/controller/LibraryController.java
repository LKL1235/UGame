package com.myApp.gamestore.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.myApp.gamestore.DTO.LibraryGameShowDTO;
import com.myApp.gamestore.DTO.LibraryDTO;
import com.myApp.gamestore.DTO.GameShowDTO;
import com.myApp.gamestore.entity.Game;
import com.myApp.gamestore.entity.Library;
import com.myApp.gamestore.service.GameService;
import com.myApp.gamestore.service.LibraryService;
import com.myApp.gamestore.utils.ResultCode;
import com.myApp.gamestore.utils.myResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @description:
 * @author:25445
 * @date:2022/11/30 19:00
 **/
@RestController
public class LibraryController {

    private LibraryService libraryService;
    @Value("${path.BASE_URL_PATH}")
    private   String BASE_URL_PATH ;
    @Value("${path.BASE_FILE_PATH}")
    private  String BASE_FILE_PATH ;
    @Autowired
    public void setLibraryService(LibraryService libraryService) {
        this.libraryService = libraryService;
    }
    @Autowired
    private GameService gameService;

    @RequestMapping("/getLibrary/{name}")
    public myResult getLibrary(@PathVariable String name){
        QueryWrapper<Library> libraryQueryWrapper = new QueryWrapper<>();
        libraryQueryWrapper.eq("username",name);
        List<Library> list=libraryService.list(libraryQueryWrapper);
        QueryWrapper<Game> queryWrapper = new QueryWrapper<>();
        List gameIdList = new ArrayList<>();
        list.forEach(item->gameIdList.add(item.getGameId()));
        queryWrapper.in("game_id",gameIdList);

        List<Game> gameList = gameService.list(queryWrapper);

        List<LibraryGameShowDTO> LibraryList=new ArrayList<>();
        gameList.forEach((item)->{
            LibraryGameShowDTO libraryGameShowDTO = new LibraryGameShowDTO();
            libraryGameShowDTO.setGameId(item.getGameId());
            libraryGameShowDTO.setGameName(item.getGameName());
            libraryGameShowDTO.setFiles(item.getFiles());

            ArrayList<String> strings = new ArrayList<>();
            String path = BASE_FILE_PATH+item.getImages();
            File file = new File(path);
            File[] fs = file.listFiles();
            if(fs!=null){
                for(File f:fs){
                    if(!f.isDirectory()) {
                        String s=f.getName();
                        String urlPath=BASE_URL_PATH+item.getGameName()+"/"+s;
                        strings.add(urlPath);
                    }
                }
                libraryGameShowDTO.setImg(strings);
                List<String> tagStringList=new ArrayList<>();
                String[] tagString;
                tagString=item.getTags().split(",");
                Collections.addAll(tagStringList, tagString);
                libraryGameShowDTO.setTags(tagStringList);
                libraryGameShowDTO.setPrice(item.getPrice());
                LibraryList.add(libraryGameShowDTO);
            }
        });
        return new myResult(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMsg(),LibraryList);
    }
}

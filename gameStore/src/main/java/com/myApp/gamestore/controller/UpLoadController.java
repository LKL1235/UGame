package com.myApp.gamestore.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.myApp.gamestore.DTO.UpLoadDTO;
import com.myApp.gamestore.entity.Developer;
import com.myApp.gamestore.entity.Game;
import com.myApp.gamestore.entity.User;
import com.myApp.gamestore.service.DeveloperService;
import com.myApp.gamestore.service.GameService;
import com.myApp.gamestore.service.UserService;
import com.myApp.gamestore.utils.ResultCode;
import com.myApp.gamestore.utils.myResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * @description:
 * @author:25445
 * @date:2023/1/12 20:29
 **/
@RestController
public class UpLoadController {
    private static final Logger LOG = LoggerFactory.getLogger(UpLoadController.class);
    @Autowired
    private DeveloperService developerService;
    @Autowired
    private GameService gameService;
    @Autowired
    private UserService userService;

    @Value("${path.BASE_FILE_PATH}")
    private  String BASE_FILE_PATH ;
    @Value("${path.BASE_URL_PATH}")
    private   String BASE_URL_PATH ;

    @RequestMapping("/upload")
    public myResult upload(@RequestBody UpLoadDTO upLoadDTO){
        QueryWrapper queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("name",upLoadDTO.getName());
        Developer developer=developerService.getOne(queryWrapper);
        String repository=developer.getRepository();
        Game game=new Game();
        game.setGameName(upLoadDTO.getGameName());
        game.setRepository(repository);
        game.setImages(upLoadDTO.getGameName());
        game.setPrice(upLoadDTO.getPrice());
        game.setTags(upLoadDTO.getTags());
        game.setIntroduce(upLoadDTO.getIntroduce());
        game.setAbout(upLoadDTO.getAbout());
        gameService.save(game);
        return new myResult(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMsg());
    }

    @RequestMapping("/gameUpload")
    public myResult gameUpload(MultipartFile file ,@RequestParam Integer gameId){
        String originalFilename = file.getOriginalFilename();
        if (StrUtil.isBlank(originalFilename)||file.isEmpty()) {
            return new myResult(ResultCode.FAILURE.getCode(), ResultCode.FAILURE.getMsg());
        }
        String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
        String fileName = gameId+extension;
        String filePath = BASE_FILE_PATH + fileName;

        File localFile = new File(filePath);
        try {
            file.transferTo(localFile);
            UpdateWrapper updateWrapper = new UpdateWrapper<Game>();
            updateWrapper.eq("game_id",gameId);
            updateWrapper.set("files",BASE_URL_PATH+fileName);
            gameService.update(updateWrapper);
        } catch (IOException e) {
            e.printStackTrace();
            return new myResult(ResultCode.FAILURE.getCode(), ResultCode.FAILURE.getMsg());
        }

        return new myResult(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMsg());
    }

    @RequestMapping("/ImgUpload")
    public myResult ImgUpload(MultipartFile file ,@RequestParam String name){
        String originalFilename = file.getOriginalFilename();
        if (StrUtil.isBlank(originalFilename)||file.isEmpty()) {
            return new myResult(ResultCode.FAILURE.getCode(), ResultCode.FAILURE.getMsg());
        }
        String fileDirPath = BASE_FILE_PATH +name;
        String filePath = BASE_FILE_PATH +name+"\\"+ originalFilename;
        File Path=new File(fileDirPath);
        if(!Path.exists() && !Path.isDirectory()){
            Path.mkdirs();
        }
        File localFile = new File(filePath);
        try {
            file.transferTo(localFile);
        } catch (IOException e) {
            e.printStackTrace();
            return new myResult(ResultCode.FAILURE.getCode(), ResultCode.FAILURE.getMsg());
        }

        return new myResult(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMsg());
    }

    @RequestMapping("/avatarUpload")
    public myResult avatarUpload(MultipartFile file ,@RequestParam String userName){
        String originalFilename = file.getOriginalFilename();
        if (StrUtil.isBlank(originalFilename)||file.isEmpty()) {
            return new myResult(ResultCode.FAILURE.getCode(), ResultCode.FAILURE.getMsg());
        }
        String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
        String fileName = userName+extension;
        String filePath = BASE_FILE_PATH + fileName;

        File localFile = new File(filePath);
        try {
            file.transferTo(localFile);
            UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
            updateWrapper.eq("name",userName);
            updateWrapper.set("avatar",BASE_URL_PATH+fileName);
            userService.update(updateWrapper);
        } catch (IOException e) {
            e.printStackTrace();
            return new myResult(ResultCode.FAILURE.getCode(), ResultCode.FAILURE.getMsg());
        }
        return new myResult(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMsg());
    }


}

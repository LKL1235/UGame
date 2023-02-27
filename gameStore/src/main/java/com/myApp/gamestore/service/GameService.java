package com.myApp.gamestore.service;

import com.myApp.gamestore.entity.Game;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author 25445
* @description 针对表【game】的数据库操作Service
* @createDate 2022-12-09 18:05:43
*/
public interface GameService extends IService<Game> {

    List<Game> gameShow(int offset,int num);

    List<Game> searchByName(String gameName,Integer page);

    Integer total(String gameName);
}

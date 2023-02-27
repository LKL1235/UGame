package com.myApp.gamestore.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.myApp.gamestore.entity.Game;
import com.myApp.gamestore.service.GameService;
import com.myApp.gamestore.mapper.GameMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author 25445
* @description 针对表【game】的数据库操作Service实现
* @createDate 2022-12-09 18:05:43
*/
@Service
public class GameServiceImpl extends ServiceImpl<GameMapper, Game>
    implements GameService{
    @Autowired
    private GameMapper gameMapper;
    @Override
    public List<Game> gameShow(int offset,int num) {
        return gameMapper.gameShow(offset,num);
    }

    @Override
    public List<Game> searchByName(String gameName, Integer page) {
        return gameMapper.searchByName("%"+gameName+"%",(page-1)*20);
    }

    @Override
    public Integer total(String gameName) {
        return gameMapper.total("%"+gameName+"%");
    }

}





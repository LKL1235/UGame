package com.myApp.gamestore.mapper;

import com.myApp.gamestore.entity.Game;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
* @author 25445
* @description 针对表【game】的数据库操作Mapper
* @createDate 2022-12-09 18:05:42
* @Entity com.myApp.gamestore.entity.Game
*/
@Mapper
public interface GameMapper extends BaseMapper<Game> {

    List<Game> gameShow(int num);
}





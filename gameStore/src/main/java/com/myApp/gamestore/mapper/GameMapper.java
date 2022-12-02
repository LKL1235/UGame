package com.myApp.gamestore.mapper;

import com.myApp.gamestore.entity.Game;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 25445
* @description 针对表【game】的数据库操作Mapper
* @createDate 2022-12-02 16:32:17
* @Entity com.myApp.gamestore.entity.Game
*/
@Mapper
public interface GameMapper extends BaseMapper<Game> {

}





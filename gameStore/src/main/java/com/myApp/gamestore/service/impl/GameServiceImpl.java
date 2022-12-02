package com.myApp.gamestore.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.myApp.gamestore.entity.Game;
import com.myApp.gamestore.service.GameService;
import com.myApp.gamestore.mapper.GameMapper;
import org.springframework.stereotype.Service;

/**
* @author 25445
* @description 针对表【game】的数据库操作Service实现
* @createDate 2022-12-02 16:32:17
*/
@Service
public class GameServiceImpl extends ServiceImpl<GameMapper, Game>
    implements GameService{

}





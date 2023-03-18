package com.myApp.gamestore.service;

import com.myApp.gamestore.entity.Board;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author 25445
* @description 针对表【board】的数据库操作Service
* @createDate 2023-03-15 23:01:59
*/
public interface BoardService extends IService<Board> {

    List<Board> listPage(Integer page);
}

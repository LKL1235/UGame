package com.myApp.gamestore.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.myApp.gamestore.entity.Board;
import com.myApp.gamestore.service.BoardService;
import com.myApp.gamestore.mapper.BoardMapper;
import org.springframework.stereotype.Service;

/**
* @author 25445
* @description 针对表【board】的数据库操作Service实现
* @createDate 2023-03-13 23:11:29
*/
@Service
public class BoardServiceImpl extends ServiceImpl<BoardMapper, Board>
    implements BoardService{

}





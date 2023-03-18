package com.myApp.gamestore.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.myApp.gamestore.entity.Board;
import com.myApp.gamestore.service.BoardService;
import com.myApp.gamestore.mapper.BoardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author 25445
* @description 针对表【board】的数据库操作Service实现
* @createDate 2023-03-15 23:01:59
*/
@Service
public class BoardServiceImpl extends ServiceImpl<BoardMapper, Board>
    implements BoardService{

    @Autowired
    private BoardMapper boardMapper;
    @Override
    public List<Board> listPage(Integer page) {
        return boardMapper.listPage((page-1)*10);
    }
}





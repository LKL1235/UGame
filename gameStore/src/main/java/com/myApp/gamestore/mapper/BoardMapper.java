package com.myApp.gamestore.mapper;

import com.myApp.gamestore.entity.Board;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
* @author 25445
* @description 针对表【board】的数据库操作Mapper
* @createDate 2023-03-15 23:01:59
* @Entity com.myApp.gamestore.entity.Board
*/
@Mapper
public interface BoardMapper extends BaseMapper<Board> {

    List<Board> listPage(Integer page);
}





package com.myApp.gamestore.mapper;

import com.myApp.gamestore.entity.Library;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 25445
* @description 针对表【library】的数据库操作Mapper
* @createDate 2022-12-04 10:58:15
* @Entity com.myApp.gamestore.entity.Library
*/
@Mapper
public interface LibraryMapper extends BaseMapper<Library> {

}





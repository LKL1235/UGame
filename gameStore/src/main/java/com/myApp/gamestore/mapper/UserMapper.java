package com.myApp.gamestore.mapper;

import com.myApp.gamestore.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 25445
* @description 针对表【user】的数据库操作Mapper
* @createDate 2022-11-25 14:13:07
* @Entity com.myApp.gamestore.entity.User
*/
@Mapper
public interface UserMapper extends BaseMapper<User> {

    boolean createLibrary(String name);
}





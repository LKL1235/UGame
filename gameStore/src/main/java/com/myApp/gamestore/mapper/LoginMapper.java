package com.myApp.gamestore.mapper;

import com.myApp.gamestore.entity.Login;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 25445
* @description 针对表【login】的数据库操作Mapper
* @createDate 2022-11-25 14:13:07
* @Entity com.myApp.gamestore.entity.Login
*/
@Mapper
public interface LoginMapper extends BaseMapper<Login> {

}





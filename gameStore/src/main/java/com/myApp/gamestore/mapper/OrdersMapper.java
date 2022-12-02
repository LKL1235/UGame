package com.myApp.gamestore.mapper;

import com.myApp.gamestore.entity.Orders;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 25445
* @description 针对表【orders】的数据库操作Mapper
* @createDate 2022-12-02 17:34:08
* @Entity com.myApp.gamestore.entity.Orders
*/
@Mapper
public interface OrdersMapper extends BaseMapper<Orders> {

}





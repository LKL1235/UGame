package com.myApp.gamestore.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.myApp.gamestore.entity.Orders;
import com.myApp.gamestore.service.OrdersService;
import com.myApp.gamestore.mapper.OrdersMapper;
import org.springframework.stereotype.Service;

/**
* @author 25445
* @description 针对表【orders】的数据库操作Service实现
* @createDate 2022-12-02 17:34:08
*/
@Service
public class OrdersServiceImpl extends ServiceImpl<OrdersMapper, Orders>
    implements OrdersService{

}





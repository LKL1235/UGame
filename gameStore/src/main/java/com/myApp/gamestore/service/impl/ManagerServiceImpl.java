package com.myApp.gamestore.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.myApp.gamestore.entity.Manager;
import com.myApp.gamestore.service.ManagerService;
import com.myApp.gamestore.mapper.ManagerMapper;
import org.springframework.stereotype.Service;

/**
* @author 25445
* @description 针对表【manager】的数据库操作Service实现
* @createDate 2023-04-05 19:38:04
*/
@Service
public class ManagerServiceImpl extends ServiceImpl<ManagerMapper, Manager>
    implements ManagerService{

}





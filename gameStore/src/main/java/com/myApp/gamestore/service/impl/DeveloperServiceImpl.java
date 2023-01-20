package com.myApp.gamestore.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.myApp.gamestore.entity.Developer;
import com.myApp.gamestore.service.DeveloperService;
import com.myApp.gamestore.mapper.DeveloperMapper;
import org.springframework.stereotype.Service;

/**
* @author 25445
* @description 针对表【developer】的数据库操作Service实现
* @createDate 2022-11-30 19:29:35
*/
@Service
public class DeveloperServiceImpl extends ServiceImpl<DeveloperMapper, Developer>
    implements DeveloperService{

}





package com.myApp.gamestore.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.myApp.gamestore.entity.Library;
import com.myApp.gamestore.service.LibraryService;
import com.myApp.gamestore.mapper.LibraryMapper;
import org.springframework.stereotype.Service;

/**
* @author 25445
* @description 针对表【library】的数据库操作Service实现
* @createDate 2022-12-04 10:58:15
*/
@Service
public class LibraryServiceImpl extends ServiceImpl<LibraryMapper, Library>
    implements LibraryService{

}





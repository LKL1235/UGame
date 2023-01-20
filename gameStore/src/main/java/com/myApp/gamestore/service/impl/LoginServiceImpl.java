package com.myApp.gamestore.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.myApp.gamestore.entity.Login;
import com.myApp.gamestore.service.LoginService;
import com.myApp.gamestore.mapper.LoginMapper;
import org.springframework.stereotype.Service;

/**
* @author 25445
* @description 针对表【login】的数据库操作Service实现
* @createDate 2022-11-25 14:13:07
*/
@Service
public class LoginServiceImpl extends ServiceImpl<LoginMapper, Login>
    implements LoginService{

}





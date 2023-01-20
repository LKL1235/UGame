package com.myApp.gamestore.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.myApp.gamestore.DTO.LoginDTO;
import com.myApp.gamestore.entity.Login;
import com.myApp.gamestore.entity.User;
import com.myApp.gamestore.service.IsLoginVerifyService;
import com.myApp.gamestore.service.LoginService;
import com.myApp.gamestore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.myApp.gamestore.utils.ResultCode;
import com.myApp.gamestore.utils.myResult;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @description:
 * @author:25445
 * @date:2022/11/24 19:27
 **/
@RestController
public class LoginController {


    private StringRedisTemplate stringRedisTemplate;
    private IsLoginVerifyService isLoginVerifyService;

    private LoginDTO loginDTO;
    private LoginService loginService;
    private UserService userService;
    @Autowired
    private void setIsLoginVerifyService(IsLoginVerifyService isLoginVerifyService){
        this.isLoginVerifyService=isLoginVerifyService;
    }
    @Autowired
    public void setRedisTemplate(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }
    @Autowired
    public void setLoginDTO(LoginDTO loginDTO) {
        this.loginDTO = loginDTO;
    }
    @Autowired
    public void setStringRedisTemplate(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }
    @Autowired
    public void setLoginService(LoginService loginService) {
        this.loginService = loginService;
    }
    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/login")
    public myResult Login(@RequestBody LoginDTO loginDTO, @CookieValue(value = "version", defaultValue = "none") String version, HttpServletResponse response){
        String LoginStr=loginDTO.getVerifyCode();
        String code= (String) stringRedisTemplate.opsForValue().get(version);
        //验证码判断
        if(Objects.equals(code, LoginStr)) {
            QueryWrapper queryWrapper = new QueryWrapper();
            queryWrapper.eq("name",loginDTO.getName());
            queryWrapper.eq("pass_word",loginDTO.getPassWord());
            Login login=loginService.getOne(queryWrapper);
            if(login!=null){
                QueryWrapper queryWrapper1 = new QueryWrapper();
                queryWrapper1.eq("name",loginDTO.getName());
                User user=userService.getOne(queryWrapper1);
                user.setLatestDate(loginDTO.getLatestDate());
                boolean isSuccess=userService.updateById(user);
                if(isSuccess) {
                    if (loginDTO.isRemember()){
                        String whoAmI = UUID.randomUUID().toString();
                        Cookie cookie = new Cookie("whoAmI", whoAmI);
                        cookie.setMaxAge(7 * 24 * 60 * 60);
                        response.addCookie(cookie);
                        stringRedisTemplate.opsForValue().set(whoAmI, user.getName(), 7, TimeUnit.DAYS);
                    }
                    System.out.println("LoginController:"+"用户"+user.getName()+"登录成功");
                    return new myResult(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMsg(),user);
                }else {
                    return new myResult(ResultCode.FAILURE.getCode(), "登录失败");
                }
            }else {
                return new myResult(ResultCode.FAILURE.getCode(), "用户名或密码错误");
            }
        }else {
            return new myResult(ResultCode.FAILURE.getCode(), "验证码不正确");
        }
    }
    @RequestMapping("/isLogin")
    public myResult isLogin(@CookieValue(value = "whoAmI", defaultValue = "false") String whoAmI){

        String name=stringRedisTemplate.opsForValue().get(whoAmI);
        if(!Objects.equals(name, null))
        {
            QueryWrapper queryWrapper=new QueryWrapper();
            queryWrapper.eq("name",name);
            User user=userService.getOne(queryWrapper);
            return new myResult(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMsg(),user);
        }else {
            return new myResult(ResultCode.FAILURE.getCode(), "用户未登录过");
        }
    }
    @RequestMapping("/Logout")
    public myResult Logout(@CookieValue(value = "whoAmI",defaultValue = "false")String whoAmI){
        stringRedisTemplate.delete(whoAmI);
        System.out.println("LoginController:"+"登出");
        return new myResult(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMsg());
    }
}

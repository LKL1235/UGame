package com.myApp.gamestore.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.myApp.gamestore.DTO.RegisterDTO;
import com.myApp.gamestore.entity.Developer;
import com.myApp.gamestore.entity.Login;
import com.myApp.gamestore.entity.User;
import com.myApp.gamestore.service.DeveloperService;
import com.myApp.gamestore.service.LoginService;
import com.myApp.gamestore.service.UserService;
import com.myApp.gamestore.utils.ResultCode;
import com.myApp.gamestore.utils.myResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

/**
 * @description:
 * @author:25445
 * @date:2022/11/28 8:15
 **/
@RestController
@RequestMapping("/user")
public class UserController {

    private StringRedisTemplate stringRedisTemplate;
    private UserService userService;
    private LoginService loginService;
    @Autowired
    private DeveloperService developerService;
    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
    @Autowired
    public void setStringRedisTemplate(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }
    @Autowired
    public void setLoginService(LoginService loginService) {
        this.loginService = loginService;
    }

    @RequestMapping("/register")
    public myResult Register(@RequestBody RegisterDTO registerDTO, @CookieValue(value = "version", defaultValue = "none") String version){
        String VerifyCode=stringRedisTemplate.opsForValue().get(version);
        //判断验证码
        if(Objects.equals(registerDTO.getVerifyCode(), VerifyCode)){
            User user=registerDTO.toUser();
            String emailCode=registerDTO.getEmailCode();
            String code=stringRedisTemplate.opsForValue().get(registerDTO.getEmail());
            //判断邮箱验证码
            if(Objects.equals(emailCode, code)){
                QueryWrapper queryWrapper= new QueryWrapper<>();
                queryWrapper.eq("email",registerDTO.getEmail());
                Long emailCount=userService.count(queryWrapper);
                //判断邮箱是否已存在
                if(emailCount.equals(0L)){
                    QueryWrapper queryWrapper1 = new QueryWrapper();
                    queryWrapper1.eq("name",registerDTO.getName());
                    Long loginCount=loginService.count(queryWrapper1);
                    //判断用户名是否存在
                    if(loginCount.equals(0L)) {
                        Login login = new Login();
                        login.setName(registerDTO.getName());
                        login.setPassWord(registerDTO.getPassWord());
                        if(loginService.save(login)){
                            userService.save(user);
                            // userService.createLibrary(user.getName());
                            return new myResult(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMsg());
                        }else {
                            return new myResult(ResultCode.FAILURE.getCode(), "注册失败");
                        }
                    }else {
                        return new myResult(ResultCode.FAILURE.getCode(), "用户名已使用");
                    }
                }else {
                    return new myResult(ResultCode.FAILURE.getCode(), "该邮箱已注册过");
                }
            }else {
                return new myResult(ResultCode.FAILURE.getCode(), "邮箱验证码错误");
            }
        }else {
            return new myResult(ResultCode.FAILURE.getCode(), "验证码错误");
        }
    }

    @RequestMapping("/isDeveloper")
    public myResult isDeveloper (@RequestParam String name){
        QueryWrapper<Developer> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name",name);
        Developer developer = developerService.getOne(queryWrapper);
        if (developer==null){
            return new myResult(ResultCode.FAILURE.getCode(), ResultCode.FAILURE.getMsg());
        }
        return new myResult(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMsg());
    }

    @RequestMapping("/all")
    public myResult all(){

        List<User> list = userService.list();
        return new myResult(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMsg(),list);
    }

    @RequestMapping("/delete")
    public myResult delete(@RequestParam String name){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name",name);
        boolean result = userService.remove(queryWrapper);
        return new myResult(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMsg());
    }


    @RequestMapping("/forget")
    public myResult forget(@RequestBody RegisterDTO registerDTO, @CookieValue(value = "version", defaultValue = "none") String version){
        String VerifyCode=stringRedisTemplate.opsForValue().get(version);
        //判断验证码

        if(Objects.equals(registerDTO.getVerifyCode(), VerifyCode)){
            User user=registerDTO.toUser();
            String emailCode=registerDTO.getEmailCode();
            String code=stringRedisTemplate.opsForValue().get(registerDTO.getEmail());
            //判断邮箱验证码
            if(Objects.equals(emailCode, code)){
                QueryWrapper queryWrapper= new QueryWrapper<>();
                queryWrapper.eq("email",registerDTO.getEmail());
                Long emailCount=userService.count(queryWrapper);
                //判断邮箱是否已存在
                if(!emailCount.equals(0L)){
                    QueryWrapper queryWrapper1 = new QueryWrapper();
                    queryWrapper1.eq("name",registerDTO.getName());
                    Long loginCount=loginService.count(queryWrapper1);
                    //判断用户名是否存在
                    if(!loginCount.equals(0L)) {
                        Login login = new Login();
                        login.setName(registerDTO.getName());
                        login.setPassWord(registerDTO.getPassWord());
                        UpdateWrapper<Login> updateWrapper = new UpdateWrapper<>();
                        updateWrapper.eq("name",registerDTO.getName());
                        updateWrapper.set("pass_word",registerDTO.getPassWord());
                        boolean result = loginService.update(updateWrapper);
                        if(result){
                            // userService.createLibrary(user.getName());
                            return new myResult(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMsg());
                        }else {
                            return new myResult(ResultCode.FAILURE.getCode(), "重置失败");
                        }
                    }else {
                        return new myResult(ResultCode.FAILURE.getCode(), "用户名未使用");
                    }
                }else {
                    return new myResult(ResultCode.FAILURE.getCode(), "该邮箱未注册过");
                }
            }else {
                return new myResult(ResultCode.FAILURE.getCode(), "邮箱验证码错误");
            }
        }else {
            return new myResult(ResultCode.FAILURE.getCode(), "验证码错误");
        }
    }

}

package com.myApp.gamestore.controller;

import com.myApp.gamestore.service.EmailService;
import com.myApp.gamestore.utils.ResultCode;
import com.myApp.gamestore.utils.myResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @description:
 * @author:25445
 * @date:2022/11/28 7:52
 **/
@RestController
public class EmailController {

    private StringRedisTemplate stringRedisTemplate;
    private EmailService emailService;
    @Autowired
    public void setEmailService(EmailService emailService) {
        this.emailService = emailService;
    }
    @Autowired
    public void setStringRedisTemplate(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }

    @RequestMapping("/Email/{toEmail}")
    public myResult SendEmail(@PathVariable String toEmail){
        String code=String.format("%04d",(int)(Math.random()*10000));
        boolean success=emailService.sendEmail(toEmail,code);
        if (success) {
            stringRedisTemplate.opsForValue().set(toEmail,code,5, TimeUnit.MINUTES);
            return new myResult(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMsg());
        }else {
            return new myResult(ResultCode.FAILURE.getCode(), ResultCode.FAILURE.getMsg());
        }
    }
}

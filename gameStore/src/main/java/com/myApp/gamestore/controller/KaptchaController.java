package com.myApp.gamestore.controller;

import com.google.code.kaptcha.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.myApp.gamestore.utils.ResultCode;
import com.myApp.gamestore.utils.myResult;

import java.util.Base64;
import javax.imageio.ImageIO;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @description:
 * @author:25445
 * @date:2022/11/25 23:49
 **/
@RestController
public class KaptchaController {

    private StringRedisTemplate stringRedisTemplate;
    private Producer kaptcherProducer;
    @Autowired
    public void setStringRedisTemplate(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }
    @Autowired
    public void setKaptcherProducer(Producer kaptcherProducer) {
        this.kaptcherProducer = kaptcherProducer;
    }


    @RequestMapping("/Kaptcha")
    public myResult Kaptcha(HttpServletResponse response) throws IOException {
        String version=UUID.randomUUID().toString();
        Cookie cookie = new Cookie("version",version);
        cookie.setMaxAge(3*60);
        response.addCookie(cookie);
        String code = kaptcherProducer.createText();
        BufferedImage image = kaptcherProducer.createImage(code);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ImageIO.write(image, "jpg", outputStream);
        String str = "data:image/jpeg;base64,";
        String base64Img = str + Base64.getEncoder().encodeToString(outputStream.toByteArray());
        stringRedisTemplate.opsForValue().set(version,code,3, TimeUnit.MINUTES);
        System.out.println("KaptchaController:请求验证码"+code);
        return new myResult(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMsg(),base64Img);
    }


}

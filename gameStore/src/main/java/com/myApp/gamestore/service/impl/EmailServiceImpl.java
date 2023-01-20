package com.myApp.gamestore.service.impl;

import com.myApp.gamestore.service.EmailService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @description:
 * @author:25445
 * @date:2022/11/28 7:47
 **/
@Service
public class EmailServiceImpl implements EmailService {

    @Resource
    private JavaMailSender javaMailSender;
    @Value("${spring.mail.username}")
    private String fromEmail;
    @Override
    public boolean sendEmail(String toEmail, String message) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        //设置发件邮箱
        simpleMailMessage.setFrom(fromEmail);
        //收件人邮箱
        simpleMailMessage.setTo(toEmail);
        //主题标题
        simpleMailMessage.setSubject("正在创建 UGame 账户");
        //信息内容
        simpleMailMessage.setText("验证码为："+message+"\n有效期：5分钟\n若不是本人操作请忽视此邮件");
        //执行发送
        try {//发送可能失败
            javaMailSender.send(simpleMailMessage);
            //没有异常返回true，表示发送成功
            return true;
        } catch (Exception e) {
            //发送失败，返回false
            return false;
        }
    }
}

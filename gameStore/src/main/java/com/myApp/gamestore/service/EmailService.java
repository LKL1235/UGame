package com.myApp.gamestore.service;

/**
 * @description:
 * @author:25445
 * @date:2022/11/28 7:46
 **/
public interface EmailService {
    public boolean sendEmail(String toEmail, String message);
}

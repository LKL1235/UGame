package com.myApp.gamestore.DTO;

import com.myApp.gamestore.entity.User;
import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @author:25445
 * @date:2022/11/28 8:17
 **/
@Data
@Component
public class RegisterDTO extends User {
    private String emailCode;
    private String PassWord;
    private String confirmPassWord;
    private String verifyCode;
    public User toUser(){
        return new User(this.getName(),this.getEmail(),this.getRegDate());
    }
}

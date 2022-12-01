package com.myApp.gamestore.DTO;

import com.myApp.gamestore.entity.Login;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Date;


/**
 * @description:
 * @author:25445
 * @date:2022/11/25 14:38
 **/
@Data
@Component
public class LoginDTO extends Login {

    private String verifyCode;

    private Date latestDate;

    private boolean remember;

}

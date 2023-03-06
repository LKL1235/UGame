package com.myApp.gamestore.DTO;

import com.myApp.gamestore.controller.AliPay;
import lombok.Data;

import java.util.Date;

/**
 * @description:
 * @author:25445
 * @date:2022/12/2 19:37
 **/
@Data
public class AliPayDTO extends AliPay {
    // private String tradeNo;
    // private double totalAmount;
    // private String subject;
    private Integer game_id;
    private String userName;
    private Date createTime;
}

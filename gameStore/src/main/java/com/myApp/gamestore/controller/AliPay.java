package com.myApp.gamestore.controller;

import lombok.Data;

@Data
public class AliPay {
    private String tradeNo;
    private double totalAmount;
    private String subject;
}

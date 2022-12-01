package com.myApp.gamestore.service;

import com.alipay.api.AlipayApiException;
import com.myApp.gamestore.entity.AliPayBean;

public interface PayService {

    String aliPay(AliPayBean aliPayBean) throws AlipayApiException;
}

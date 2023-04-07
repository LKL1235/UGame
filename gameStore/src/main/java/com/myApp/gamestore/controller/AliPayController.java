package com.myApp.gamestore.controller;

import cn.hutool.json.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.myApp.gamestore.DTO.AliPayDTO;
import com.myApp.gamestore.config.AliPayConfig;
import com.myApp.gamestore.entity.Developer;
import com.myApp.gamestore.entity.Game;
import com.myApp.gamestore.entity.Library;
import com.myApp.gamestore.mapper.OrdersMapper;
import com.myApp.gamestore.entity.Orders;
import com.myApp.gamestore.service.DeveloperService;
import com.myApp.gamestore.service.GameService;
import com.myApp.gamestore.service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

// xjlugv6874@sandbox.com
// 9428521.24 - 30 = 9428491.24 + 30 = 9428521.24
@RestController
@RequestMapping("/alipay")
public class AliPayController {

    private static final String GATEWAY_URL = "https://openapi.alipaydev.com/gateway.do";
    private static final String FORMAT = "JSON";
    private static final String CHARSET = "UTF-8";
    //签名方式
    private static final String SIGN_TYPE = "RSA2";

    @Resource
    private AliPayConfig aliPayConfig;

    @Resource
    private OrdersMapper ordersMapper;

    @Autowired
    private GameService gameService;
    @Autowired
    private LibraryService libraryService;
    @Autowired
    private DeveloperService developerService;

    @PostMapping("/developerPay") // &subject=xxx&traceNo=xxx&totalAmount=xxx
    public String developerPay(@RequestBody AliPayDTO aliPayDTO) throws Exception {
        aliPayDTO.setTradeNo(System.currentTimeMillis()+"0");
        aliPayDTO.setSubject(aliPayDTO.getUserName()+"developerPay");
        aliPayDTO.setTotalAmount(100.00);
        // 1. 创建Client，通用SDK提供的Client，负责调用支付宝的API
        AlipayClient alipayClient = new DefaultAlipayClient(GATEWAY_URL, aliPayConfig.getAppId(),
                aliPayConfig.getAppPrivateKey(), FORMAT, CHARSET, aliPayConfig.getAlipayPublicKey(), SIGN_TYPE);
        // 2. 创建 Request并设置Request参数
        AlipayTradePagePayRequest request = new AlipayTradePagePayRequest();  // 发送请求的 Request类
        request.setNotifyUrl(aliPayConfig.getNotifyUrl());
        request.setReturnUrl(aliPayConfig.getReturnUrl());
        JSONObject bizContent = new JSONObject();
        bizContent.set("out_trade_no", aliPayDTO.getTradeNo());  // 我们自己生成的订单编号
        bizContent.set("total_amount", aliPayDTO.getTotalAmount()); // 订单的总金额
        bizContent.set("subject", aliPayDTO.getSubject());   // 支付的名称
        bizContent.set("product_code", "FAST_INSTANT_TRADE_PAY");  // 固定配置
        bizContent.set("timeout_express","15m");
        request.setBizContent(bizContent.toString());

        ordersMapper.insert(new Orders(null,null,aliPayDTO.getSubject(),aliPayDTO.getTradeNo(),null,aliPayDTO.getCreateTime(),null,"未支付", aliPayDTO.getTotalAmount()));
        // 执行请求，拿到响应的结果，返回给浏览器
        String form = "";
        try {
            form = alipayClient.pageExecute(request).getBody(); // 调用SDK生成表单
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        // httpResponse.setContentType("text/html;charset=" + CHARSET);
        // httpResponse.getWriter().write(form);// 直接将完整的表单html输出到页面
        // httpResponse.getWriter().flush();
        // httpResponse.getWriter().close();
        return form;
    }

    @PostMapping("/gamePay") // &subject=xxx&traceNo=xxx&totalAmount=xxx
    public String gamePay(@RequestBody AliPayDTO aliPayDTO) throws Exception {
        aliPayDTO.setTradeNo(System.currentTimeMillis()+"0");
        aliPayDTO.setSubject(aliPayDTO.getUserName());
        // System.out.println(aliPayDTO.getTradeNo());
        // System.out.println(aliPayDTO.getSubject());
        System.out.println(aliPayDTO.getTotalAmount());
        if(aliPayDTO.getTotalAmount()==0){
            ordersMapper.insert(new Orders(null,aliPayDTO.getGame_id(),aliPayDTO.getSubject(),aliPayDTO.getTradeNo(),null,aliPayDTO.getCreateTime(),null,"已支付", aliPayDTO.getTotalAmount()));
            Game game = gameService.getById(aliPayDTO.getGame_id());
            libraryService.save(new Library(null,aliPayDTO.getSubject(),aliPayDTO.getGame_id(),game.getGameName()));
            return "购买完成";
        }else{
        // aliPayDTO.setTotalAmount(100.00);
        // 1. 创建Client，通用SDK提供的Client，负责调用支付宝的API
        AlipayClient alipayClient = new DefaultAlipayClient(GATEWAY_URL, aliPayConfig.getAppId(),
                aliPayConfig.getAppPrivateKey(), FORMAT, CHARSET, aliPayConfig.getAlipayPublicKey(), SIGN_TYPE);
        // 2. 创建 Request并设置Request参数
        AlipayTradePagePayRequest request = new AlipayTradePagePayRequest();  // 发送请求的 Request类
        request.setNotifyUrl(aliPayConfig.getNotifyUrl());
        request.setReturnUrl(aliPayConfig.getReturnUrl());
        JSONObject bizContent = new JSONObject();
        bizContent.set("out_trade_no", aliPayDTO.getTradeNo());  // 我们自己生成的订单编号
        bizContent.set("total_amount", aliPayDTO.getTotalAmount()); // 订单的总金额
        bizContent.set("subject", aliPayDTO.getSubject());   // 支付的名称
        bizContent.set("product_code", "FAST_INSTANT_TRADE_PAY");  // 固定配置
        bizContent.set("timeout_express","15m");
        request.setBizContent(bizContent.toString());

        ordersMapper.insert(new Orders(null,aliPayDTO.getGame_id(),aliPayDTO.getSubject(),aliPayDTO.getTradeNo(),null,aliPayDTO.getCreateTime(),null,"未支付", aliPayDTO.getTotalAmount()));
        // 执行请求，拿到响应的结果，返回给浏览器
        String form = "";
        try {
            form = alipayClient.pageExecute(request).getBody(); // 调用SDK生成表单
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }

        return form;
        }
    }

    @PostMapping("/notify")  // 注意这里必须是POST接口
    public String payNotify(HttpServletRequest request) throws Exception {
        if (request.getParameter("trade_status").equals("TRADE_SUCCESS")) {
            System.out.println("=========支付宝异步回调========");

            Map<String, String> params = new HashMap<>();
            Map<String, String[]> requestParams = request.getParameterMap();
            for (String name : requestParams.keySet()) {
                params.put(name, request.getParameter(name));
                // System.out.println(name + " = " + request.getParameter(name));
            }

            String outTradeNo = params.get("out_trade_no");
            String gmtPayment = params.get("gmt_payment");
            String alipayTradeNo = params.get("trade_no");

            String sign = params.get("sign");
            String content = AlipaySignature.getSignCheckContentV1(params);
            boolean checkSignature = AlipaySignature.rsa256CheckContent(content, sign, aliPayConfig.getAlipayPublicKey(), "UTF-8"); // 验证签名
            // 支付宝验签
            if (checkSignature) {
                // 验签通过
                System.out.println("交易名称: " + params.get("subject"));
                System.out.println("交易状态: " + params.get("trade_status"));
                System.out.println("支付宝交易凭证号: " + params.get("trade_no"));
                System.out.println("商户订单号: " + params.get("out_trade_no"));
                System.out.println("交易金额: " + params.get("total_amount"));
                System.out.println("买家在支付宝唯一id: " + params.get("buyer_id"));
                System.out.println("买家付款时间: " + params.get("gmt_payment"));
                System.out.println("买家付款金额: " + params.get("buyer_pay_amount"));

                // 查询订单
                QueryWrapper<Orders> queryWrapper = new QueryWrapper<>();
                queryWrapper.eq("order_id", outTradeNo);
                Orders orders = ordersMapper.selectOne(queryWrapper);

                if (orders != null) {
                    orders.setAlipayId(alipayTradeNo);
                    orders.setPayTime(new Date());
                    orders.setState("已支付");
                    ordersMapper.updateById(orders);
                    Integer gameId = orders.getGameId();
                    if(gameId!=null) {
                        QueryWrapper<Game> gameQueryWrapper = new QueryWrapper<>();
                        gameQueryWrapper.eq("game_id", gameId);
                        Game game = gameService.getOne(gameQueryWrapper);
                        libraryService.save(new Library(null, orders.getName(), gameId, game.getGameName()));
                    }else{
                        String name = orders.getName().replace("developerPay","");
                        developerService.save(new Developer(name,0.0,0.0,name));
                    }
                }
            }
        }
        return "success";
    }
}

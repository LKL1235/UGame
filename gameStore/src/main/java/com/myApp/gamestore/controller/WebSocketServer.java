package com.myApp.gamestore.controller;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.myApp.gamestore.entity.Message;
import com.myApp.gamestore.service.MessageService;
import com.myApp.gamestore.service.impl.MessageServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author websocket服务
 */
@ServerEndpoint(value = "/ws/{username}")
@Component
public class WebSocketServer {
    private static final Logger log = LoggerFactory.getLogger(WebSocketServer.class);
    public static final Map<String, Session> sessionMap = new ConcurrentHashMap<>();


    private static ApplicationContext applicationContext;

    public static void setApplicationContext(ApplicationContext context) {
        applicationContext = context;
    }


    @OnOpen
    public void onOpen(Session session,@PathParam("username") String username) {
        sessionMap.put(username, session);
            log.info("有用户上线，username={}, 当前在线人数为：{}", username, sessionMap.size());
    }
    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose(Session session, @PathParam("username") String username) {
        sessionMap.remove(username);
        log.info("有一连接关闭，移除username={}的用户session, 当前在线人数为：{}", username, sessionMap.size());
    }
    /**
     * 收到客户端消息后调用的方法
     * 后台收到客户端发送过来的消息
     * onMessage 是一个消息的中转站
     * 接受 浏览器端 socket.send 发送过来的 json数据
     * @param message 客户端发送过来的消息
     */
    @OnMessage
    public void onMessage(String message, Session session,@PathParam("username") String username) {
        log.info("服务端收到用户username={}的消息:{}", username, message);
        JSONObject obj = JSONUtil.parseObj(message);
        String type = obj.getStr("type");

        if ("message".equals(type)){
            String fromUserName = obj.getStr("fromUserName");
            String avatar = obj.getStr("avatar");
            String toUserName = obj.getStr("toUserName");
            String content = obj.getStr("content");
            Date timeStamp = obj.getDate("timeStamp");
            MessageService messageService = applicationContext.getBean(MessageService.class);
            messageService.save(new Message(null,avatar,fromUserName,toUserName,content,"text",timeStamp));
            if (sessionMap.containsKey(toUserName)) {
                sendMessage(message, sessionMap.get(toUserName));
            }
        } else if (Objects.equals(type,"refresh")) {
            String toUserName = obj.getStr("toUserName");
            if(sessionMap.containsKey(toUserName)){
                sendMessage(message,sessionMap.get(toUserName));
            }
        }/*else if (Objects.equals(type,"apply")) {

        }*/

        // boolean isPause=(boolean)obj.get("isPause");
        // {"to": "admin", "text": "聊天文本"}

    }
    @OnError
    public void onError(Session session, Throwable error) {
        log.error("发生错误");
        error.printStackTrace();
    }
    /**
     * 服务端发送消息给客户端
     */
    private void sendMessage(String message, Session toSession) {
        try {
            log.info("服务端给客户端[{}]发送消息{}", toSession.getId(), message);
            toSession.getBasicRemote().sendText(message);
        } catch (Exception e) {
            log.error("服务端发送消息给客户端失败", e);
        }
    }
    /**
     * 服务端发送消息给所有客户端
     */
    private void sendAllMessage(String message) {
        try {
            for (Session session : sessionMap.values()) {
                log.info("服务端给客户端[{}]发送消息{}", session.getId(), message);
                session.getBasicRemote().sendText(message);
            }
        } catch (Exception e) {
            log.error("服务端发送消息给客户端失败", e);
        }
    }
}

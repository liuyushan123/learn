package com.liuyushan;

import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;

/**
 * @author lys
 * 2021/10/11
 */
@ServerEndpoint(value="/websocketTest/{userId}")
@Component
public class Test {

    private String userId;

    //连接时执行
    @OnOpen
    public void onOpen(@PathParam("userId") String userId, Session session) throws IOException {
        this.userId = userId;
        System.out.printf("新连接：%s",userId);
    }

    //关闭时执行
    @OnClose
    public void onClose(){
        System.out.printf("连接：%s 关闭",this.userId);
    }

    //收到消息时执行
    @OnMessage
    public void onMessage(String message, Session session) throws IOException {
        System.out.printf("收到用户%s的消息%s",this.userId,message);
        session.getBasicRemote().sendText("收到 "+this.userId+" 的消息 "); //回复用户
    }

    //连接错误时执行
    @OnError
    public void onError(Session session, Throwable error){
        System.out.printf("用户id为：%s的连接发送错误",this.userId);
        error.printStackTrace();
    }

}

package com.fly.view;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * 消息服务器,保证实时向用户推送消息
 * @author fly
 */
@ServerEndpoint("/webSocket/{token}")
public class WebSocketServer {

    /**
     * 保证每个客户端与服务器拥有单一线程
     */
    public static CopyOnWriteArraySet<WebSocketServer> webSocketServers
            =  new CopyOnWriteArraySet<WebSocketServer>();
    /**
     * 与每个客户端的连接回话
     */
    private Session session;

    @OnOpen
    public void onOpen(Session session, @PathParam(value = "token") String token) {
        this.session = session;
        webSocketServers.add(this);
    }

    /**
     * 关闭客户端连接
     */
    @OnClose
    public void onClose(){
        webSocketServers.remove(this);
    }

    @OnMessage
    public void onMessage(String message){
        for (WebSocketServer item : webSocketServers){
            try {
                item.sendMessage(message);
            } catch (IOException e) {
                continue;
            }
        }
    }

    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }

}

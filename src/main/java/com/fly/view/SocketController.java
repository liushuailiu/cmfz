package com.fly.view;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @author fly
 */
@ServerEndpoint("/soc")
public class SocketController {

    private Session session;

    public static CopyOnWriteArraySet<SocketController> controllers =
            new CopyOnWriteArraySet<SocketController>();

    @OnOpen
    public void onOpen(Session session){
        this.session = session;
        controllers.add(this);
    }

    @OnClose
    public void onClose() {
        controllers.remove(this);
        System.out.println("删除链接");
    }

    @OnMessage
    public void onMessage(String message){
        System.out.println(message);
    }

    public void onSendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }

}

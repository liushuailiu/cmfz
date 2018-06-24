package com.fly.service.rabbit;

import com.fly.view.WebSocketServer;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * @author fly
 * 消息消费者
 */
@Service("messageCustomer")
public class Customer implements MessageListener {

    @Override
    public void onMessage(Message message) {

        for (WebSocketServer webSocketServer : WebSocketServer.webSocketServers){
            try {
                webSocketServer.sendMessage(new String(message.getBody(),"UTF-8"));
            } catch (IOException e) {
                e.printStackTrace();
                continue;
            }
        }

    }
}

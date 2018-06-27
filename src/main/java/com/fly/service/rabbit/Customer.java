package com.fly.service.rabbit;

import com.fly.view.SocketController;
import com.fly.view.TestController;
import com.fly.view.WebSocketServer;
import org.aspectj.weaver.ast.Test;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
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

        for (SocketController s : SocketController.controllers){
            try {
                s.onSendMessage(new String(message.getBody(),"UTF-8"));
            } catch (IOException e) {
                continue;
            }
        }

    }

}

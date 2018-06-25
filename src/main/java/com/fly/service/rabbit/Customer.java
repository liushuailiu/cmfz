package com.fly.service.rabbit;

import com.fly.view.WebSocketServer;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author fly
 * 消息消费者
 */
@Service("messageCustomer")
public class Customer implements MessageListener {

    private List<Boolean> list = new ArrayList<>();
    private static final Integer size = 3;
    private static Boolean type = true;

    @Override
    public void onMessage(Message message) {

        try {
            boolean flag = Boolean.valueOf(new String(message.getBody(),"UTF-8"));
            list.add(flag);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        if(list.size()==size){
            for (Boolean b : list) {
                if(!b){
                    type = false;
                    continue;
                }
            }
            list.clear();
            System.out.println(type);
        }

    }


//            for (WebSocketServer webSocketServer : WebSocketServer.webSocketServers){
//        try {
//            webSocketServer.sendMessage(new String(message.getBody(),"UTF-8"));
//        } catch (IOException e) {
//            e.printStackTrace();
//            continue;
//        }
//    }
}

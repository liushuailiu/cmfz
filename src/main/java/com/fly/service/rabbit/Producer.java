package com.fly.service.rabbit;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author fly
 * 消息生产者
 */
@Service
public class Producer {

    @Autowired
    private AmqpTemplate amqpTemplate;

    /**
     *
     * @param exchange  交换机地址
     * @param queue     队列地址
     * @param object    发送对象
     */
    public void setMessageForQueue(String exchange,String queue,Object object) {
        //发送消息
        amqpTemplate.convertAndSend(exchange,queue,object);
    }


}

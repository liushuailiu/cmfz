package com.fly.service.rabbit;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
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

    @Async
    public void testEmail() throws InterruptedException {
        this.setMessageForQueue("mq_exchange","mq_url",true);
        System.out.println("a1");
        Thread.sleep(3000);
        System.out.println("a2");
    }

    @Async
    public void testPhone() throws InterruptedException {
        System.out.println("a3");
        this.setMessageForQueue("mq_exchange","mq_url",true);
        Thread.sleep(3000);
        System.out.println("a4");
    }

    @Async
    public void testVerification() throws InterruptedException {
        System.out.println("a5");
        this.setMessageForQueue("mq_exchange","mq_url",false);
        Thread.sleep(3000);
        System.out.println("a6");
    }

}

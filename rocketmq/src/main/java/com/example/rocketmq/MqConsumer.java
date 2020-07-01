package com.example.rocketmq;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;

/**
 * @author haifeng.pang [panghaifeng@weidian.com]
 * @version 1.0 : MqConsumer v0.1 2020/6/30 19:13 haifeng.pang Exp $
 **/
public class MqConsumer implements LifeCycle{

    private DefaultMQPushConsumer consumer = new DefaultMQPushConsumer();

    @Override
    public void init() throws Exception{
        consumer.setConsumerGroup("local");
        consumer.setNamesrvAddr("47.101.33.17:9876");
        consumer.subscribe("testMQ", "*");

    }

    @Override
    public void startup() throws Exception{
        consumer.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
                System.out.printf("%s Receive New Messages: %s %n", Thread.currentThread().getName(), msgs);
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });
        consumer.start();
        System.out.println("consumer start");
    }

    @Override
    public void shutdown() {
        consumer.shutdown();
    }
}

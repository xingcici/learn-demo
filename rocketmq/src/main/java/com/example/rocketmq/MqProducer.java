package com.example.rocketmq;

import com.alibaba.fastjson.JSON;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;

/**
 * @author haifeng.pang [panghaifeng@weidian.com]
 * @version 1.0 : MQProcuder v0.1 2020/6/30 19:12 haifeng.pang Exp $
 **/
public class MqProducer implements LifeCycle {

    private DefaultMQProducer producer = new DefaultMQProducer();

    @Override
    public void init() throws Exception {
        producer.setProducerGroup("local");
        producer.setNamesrvAddr("47.101.33.17:9876");
    }

    @Override
    public void startup() throws Exception {
        producer.start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        SendResult sendResult = producer.send(new Message("testMQ", "我是 body".getBytes()));
                        System.out.println("producer " + JSON.toJSONString(sendResult));
                        Thread.sleep(1000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
        System.out.println("producer start");
    }

    @Override
    public void shutdown() {
        producer.shutdown();
    }
}

package com.example.rocketmq;

/**
 * @author haifeng.pang [panghaifeng@weidian.com]
 * @version 1.0 : Bootstrap v0.1 2020/6/30 19:15 haifeng.pang Exp $
 **/
public class Bootstrap {

    public static void main(String[] args) throws Exception{
        MqConsumer consumer = new MqConsumer();
        consumer.init();
        consumer.startup();

        MqProducer producer = new MqProducer();
        producer.init();
        producer.startup();

        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            @Override
            public void run() {
                producer.shutdown();
                consumer.shutdown();
            }
        }));
    }
}

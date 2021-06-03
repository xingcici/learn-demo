package com.example.rocketmq;

import com.alibaba.fastjson.JSON;
import org.apache.rocketmq.common.message.MessageDecoder;
import org.apache.rocketmq.common.message.MessageId;
import org.junit.Test;

public class BootstrapTest{

    @Test
    public void test() throws Exception{
        MessageId messageId = MessageDecoder.decodeMessageId("2F65211100002A9F0000000000001629");
        System.out.println(JSON.toJSONString(messageId));
    }
}
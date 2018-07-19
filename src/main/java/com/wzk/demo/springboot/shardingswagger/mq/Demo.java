package com.wzk.demo.springboot.shardingswagger.mq;
import org.apache.rocketmq.common.message.Message;

public class Demo {

    public static void main(String[] args) {

        String mqNameServer = "127.0.0.1:9876";
        String mqTopics = "sendMsgTest";

        String producerMqGroupName = "DESKTOP-351UL3C";
        RocketMQProducer mqProducer = new RocketMQProducer(mqNameServer, producerMqGroupName, mqTopics);
        mqProducer.init();


        for (int i = 0; i < 5; i++) {
            Message message = new Message();
            message.setBody(("I send message to RocketMQ " + i).getBytes());
            mqProducer.send(message);
        }
        



    }
}

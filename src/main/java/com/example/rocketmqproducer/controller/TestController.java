package com.example.rocketmqproducer.controller;

import com.alibaba.fastjson.JSON;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
@RestController
public class TestController {

    @Autowired
    private UserProducerConfig userProducerConfig;

    @GetMapping("/test")
    public void TestSend() {
        DefaultMQProducer producer = userProducerConfig.getDefaultProducer();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        User user = new User("小明22", 20, "男", sdf.format(new Date()));
        Message messageToUser = new Message("topic2020", "test2020", JSON.toJSONString(user).getBytes());
        try {
            producer.send(messageToUser);
            System.out.println("消息1发送成功：" + user.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

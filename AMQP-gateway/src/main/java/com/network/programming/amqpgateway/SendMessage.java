package com.network.programming.amqpgateway;


import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SendMessage {
    @Autowired
    private RabbitTemplate iotData;


    @Bean
    public void run () throws InterruptedException {
        for (int i = 1; i<=10;i++) {
            String msg = new String("Enable "+i);

            iotData.convertAndSend("IOTController","IOTController",msg);
            Thread.sleep(1000);
        }
    }
}

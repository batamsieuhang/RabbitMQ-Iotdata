package com.network.programming.amqpnode;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AmqpNodeApplication implements CommandLineRunner {

    @Autowired
    private RabbitTemplate iotData;
    public static void main(String[] args) {
        SpringApplication.run(AmqpNodeApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        for(int i = 0 ; i<10; i++){
            String msg = "Test connect" + i;
            iotData.convertAndSend("IOT_data","IOT_data",msg);
        }
    }
}

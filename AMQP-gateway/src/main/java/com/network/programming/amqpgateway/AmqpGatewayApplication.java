package com.network.programming.amqpgateway;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AmqpGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(AmqpGatewayApplication.class, args);
    }

    @Bean
    public CommandLineRunner runner(IotDataRepository iotDataRepository) {
        return args -> {
            IotJava iotJava = new IotJava();
        };
    }


}


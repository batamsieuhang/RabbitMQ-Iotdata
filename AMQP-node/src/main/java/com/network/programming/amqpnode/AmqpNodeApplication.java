package com.network.programming.amqpnode;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.opencsv.CSVReader;

import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SpringBootApplication
public class AmqpNodeApplication{



    public static void main(String[] args) {
        SpringApplication.run(AmqpNodeApplication.class, args);
    }



}


package com.network.programming.amqpnode;


import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.opencsv.CSVReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class AmqpNodeApplication implements CommandLineRunner {

    @Autowired
    private RabbitTemplate iotData;
    public static void main(String[] args) {
        SpringApplication.run(AmqpNodeApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        String csvFilePath = "/home/mr8/project/network-programming-group6/AMQP-node/src/main/resources/house-0.csv";
        CSVReader reader = new CSVReader(new FileReader(csvFilePath));

        String[] nextLine;
        while ((nextLine = reader.readNext()) != null) {

            if ((Integer.parseInt(nextLine[5])==1) && (Integer.parseInt(nextLine[4])==0)&&(Integer.parseInt(nextLine[3])==1)) {
                int id = Integer.parseInt(nextLine[0]);
                long unixTime = Long.parseLong(nextLine[1]);
                double watValue = Double.parseDouble(nextLine[2]);
                int workLoad = Integer.parseInt(nextLine[3]);
                int plugId = Integer.parseInt(nextLine[4]);
                int houseHoldId = Integer.parseInt(nextLine[5]);
                int houseId = Integer.parseInt(nextLine[6]);

                String msg = String.format("{\"id\": %d, \"unixTime\": %d, \"watValue\": %.3f, \"workLoad\": %d, \"plugId\": %d, \"houseHoldId\": %d, \"houseId\": %d}",
                        id, unixTime, watValue, workLoad, plugId, houseHoldId, houseId);


                iotData.convertAndSend("IOT_data","IOT_data",msg);
                System.out.println(msg);
                Thread.sleep(500);
            }


        }

    }



}

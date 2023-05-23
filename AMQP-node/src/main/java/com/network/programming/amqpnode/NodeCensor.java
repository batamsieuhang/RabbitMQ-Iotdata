package com.network.programming.amqpnode;
import com.opencsv.CSVReader;

import java.io.FileNotFoundException;
import java.io.FileReader;

import com.opencsv.CSVReader;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.FileReader;
import java.io.IOException;


public class NodeCensor {


    private String getRoutingKeyByPlugId(int plugId) {
        switch (plugId) {
            case 1:
                return "IOTQueue";
            case 2:
                return "IOTQueue2";
            case 3:
                return "IOTQueue3";
            case 4:
                return "IOTQueue4";
            case 5:
                return "IOTQueue5";
            case 6:
                return "IOTQueue6";
            case 7:
                return "IOTQueue7";
            case 8:
                return "IOTQueue8";
            case 9:
                return "IOTQueue9";
            case 10:
                return "IOTQueue10";

            // Add more cases for other plugIds and routing keys
            default:
                throw new IllegalArgumentException("Invalid plugId");
        }
    }




    public void processData(RabbitTemplate iotData, int plugId) throws IOException, InterruptedException {
        String csvFilePath = "/home/mr8/project/network-programming-group6/AMQP-node/src/main/resources/debs40houses16h/house-0.csv";
        CSVReader reader = new CSVReader(new FileReader(csvFilePath));

        String[] nextLine;
        while ((nextLine = reader.readNext()) != null) {

            if ((Integer.parseInt(nextLine[5]) == 1) && (Integer.parseInt(nextLine[4]) == plugId) && (Integer.parseInt(nextLine[3]) == 1)&&(Double.parseDouble(nextLine[2])!=0)) {
                int id = Integer.parseInt(nextLine[0]);
                long unixTime = Long.parseLong(nextLine[1]);
                double watValue = Double.parseDouble(nextLine[2]);
                int workLoad = Integer.parseInt(nextLine[3]);
                int houseHoldId = Integer.parseInt(nextLine[5]);
                int houseId = Integer.parseInt(nextLine[6]);

                String msg = String.format("{\"id\": %d, \"unixTime\": %d, \"watValue\": %.3f, \"workLoad\": %d, \"plugId\": %d, \"houseHoldId\": %d, \"houseId\": %d}",
                        id, unixTime, watValue, workLoad, plugId, houseHoldId, houseId);

                String routingKey = getRoutingKeyByPlugId(plugId);
                iotData.convertAndSend("IOT_data", routingKey, msg);
                System.out.println(msg);
                Thread.sleep(100);
            }

        }
    }
}

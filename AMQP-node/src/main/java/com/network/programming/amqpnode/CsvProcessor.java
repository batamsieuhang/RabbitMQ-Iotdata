package com.network.programming.amqpnode;


import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.opencsv.CSVReader;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class CsvProcessor {

    public static void main(String[] args) throws IOException {
        String csvFilePath = "/home/mr8/project/network-programming-group6/AMQP-node/src/main/resources/house-0.csv";
        CSVReader reader = new CSVReader(new FileReader(csvFilePath));

        String[] nextLine;
        while ((nextLine = reader.readNext()) != null) {
            int id = Integer.parseInt(nextLine[0]);
            long unixTime = Long.parseLong(nextLine[1]);
            double value = Double.parseDouble(nextLine[2]);
            int workLoad = Integer.parseInt(nextLine[3]);
            int plugId = Integer.parseInt(nextLine[4]);
            int houseHoldId = Integer.parseInt(nextLine[5]);
            int houseId = Integer.parseInt(nextLine[6]);

            String msg = String.format("{\"id\": %d, \"unixTime\": %d, \"value\": %.3f, \"workLoad\": %d, \"plugId\": %d, \"houseHoldId\": %d, \"houseId\": %d}",
                    id, unixTime, value, workLoad, plugId, houseHoldId, houseId);

            Gson gson = new Gson();
            JsonObject jsonObject = gson.fromJson(msg, JsonObject.class);

            String id_query = jsonObject.get("id").getAsString();
            System.out.println(id_query);
            System.out.println(msg);
        }

    }
}

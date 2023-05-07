package com.network.programming.amqpnode;



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
            int flag1 = Integer.parseInt(nextLine[3]);
            int flag2 = Integer.parseInt(nextLine[4]);
            int flag3 = Integer.parseInt(nextLine[5]);
            int flag4 = Integer.parseInt(nextLine[6]);

            String msg = String.format("{\"id\": %d, \"unix_time\": %d, \"value\": %.3f, \"flag1\": %d, \"flag2\": %d, \"flag3\": %d, \"flag4\": %d}",
                    id, unixTime, value, flag1, flag2, flag3, flag4);


            // Process the data record here...
            System.out.printf("id: %d, unix_time: %d, value: %.3f, flag1: %d, flag2: %d, flag3: %d, flag4: %d\n",
                    id, unixTime, value, flag1, flag2, flag3, flag4);
        }
    }

}

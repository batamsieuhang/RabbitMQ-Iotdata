package com.network.programming.amqpnode;

import com.opencsv.CSVReader;
import com.rabbitmq.client.*;

import java.io.FileReader;
import java.io.IOException;

public class AmqpNodeApplication {

    private static final String EXCHANGE_NAME = "IOT_data";
    private static final String ROUTING_KEY = "IOT_data";
    private static final String QUEUE_NAME = "IOTQueue";

    private static final long MEASUREMENT_INTERVAL_MS = 1000; // Measurement interval in milliseconds

    public static void main(String[] args) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        factory.setUsername("guest");
        factory.setPassword("guest");

        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
            channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.TOPIC, true);
            channel.queueDeclare(QUEUE_NAME, true, false, false, null);
            channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, ROUTING_KEY);

            String csvFilePath = "C:\\Users\\darkarchorn\\Desktop\\LTM\\network-programming-group6\\AMQP-node\\src\\main\\resources\\house-0.csv";
            CSVReader reader = new CSVReader(new FileReader(csvFilePath));

            String[] nextLine;
            long startTime = System.currentTimeMillis();
            long messageCount = 0;

            while ((nextLine = reader.readNext()) != null) {
                int id = Integer.parseInt(nextLine[0]);
                long unixTime = Long.parseLong(nextLine[1]);
                double watValue = Double.parseDouble(nextLine[2]);
                int workLoad = Integer.parseInt(nextLine[3]);
                int plugId = Integer.parseInt(nextLine[4]);
                int houseHoldId = Integer.parseInt(nextLine[5]);
                int houseId = Integer.parseInt(nextLine[6]);

                String msg = String.format("{\"id\": %d, \"unixTime\": %d, \"watValue\": %.3f, \"workLoad\": %d, \"plugId\": %d, \"houseHoldId\": %d, \"houseId\": %d}",
                        id, unixTime, watValue, workLoad, plugId, houseHoldId, houseId);

                channel.basicPublish(EXCHANGE_NAME, ROUTING_KEY, null, msg.getBytes("UTF-8"));

                messageCount++;

                if (System.currentTimeMillis() - startTime >= MEASUREMENT_INTERVAL_MS) {
                    double throughput = (double) messageCount / (MEASUREMENT_INTERVAL_MS / 1000.0);
                    System.out.println("Throughput: " + throughput + " messages/second");

                    startTime = System.currentTimeMillis();
                    channel.basicConsume(QUEUE_NAME, false, new DefaultConsumer(channel) {
                        @Override
                        public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                            long consumerStartTime = System.currentTimeMillis();

                            String message = new String(body, "UTF-8");

                            long consumerEndTime = System.currentTimeMillis();
                            long consumerLatency = consumerEndTime - consumerStartTime;
                            System.out.println("Consumer Latency: " + consumerLatency + " milliseconds");

                            channel.basicAck(envelope.getDeliveryTag(), false);
                        }
                    });
                    messageCount = 0;
                }

                //System.out.println(msg);
            }
        }
    }
}

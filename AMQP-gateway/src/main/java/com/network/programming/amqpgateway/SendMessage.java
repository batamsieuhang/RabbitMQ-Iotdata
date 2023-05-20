//package com.network.programming.amqpgateway;
//
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class SendMessage {
//    @Autowired
//    private RabbitTemplate iotData;
//
//    @Autowired
//    private CensorRepository censorRepository;
//
//    @Bean
//    public void run() throws InterruptedException {
//        while (true) {
//            // Retrieve entries from the database where status is "on" and have_turn_on is "no"
//            Iterable<Censor> statusCensors = censorRepository.findByNameAndStatus(2, "on", "no");
//
//            for (Censor censor : statusCensors) {
//                int plugId = censor.getName(); // Assuming name field in Censor corresponds to plugId
//                String msg = "Enable " + plugId;
//
//                // Send message to IOTController
//                iotData.convertAndSend("IOTController", "IOTController", msg);
//
//                // Update have_turn_on field to "yes"
//                censor.setHave_turn_on("yes");
//                censorRepository.save(censor);
//
//                Thread.sleep(1000);
//            }
//
//            // Sleep for a certain duration before checking again
//            Thread.sleep(5000); // Adjust the duration as needed
//        }
//    }
//}

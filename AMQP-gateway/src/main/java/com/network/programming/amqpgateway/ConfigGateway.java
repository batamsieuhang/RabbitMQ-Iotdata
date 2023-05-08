package com.network.programming.amqpgateway;


import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.MessageListenerContainer;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigGateway {

    @Autowired
    IOTMessage iotMessage;
    private static final String My_Queue = "IOTQueue";
    @Bean
    Queue myQueue() {
        return new Queue(My_Queue,true);
    }

    @Bean
    Exchange iotExchange() {
        return ExchangeBuilder.topicExchange("IOT_data").durable(true).build();
    }

    @Bean
    Binding iotBinding() {
        return BindingBuilder.bind(myQueue())
                .to(iotExchange())
                .with("IOT_data")
                .noargs();
    }


    @Bean
    ConnectionFactory connectionFactory() {
        CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory("localhost");
        cachingConnectionFactory.setUsername("guest");
        cachingConnectionFactory.setPassword("guest");
        return cachingConnectionFactory;
    }

    @Bean
    MessageListenerContainer messageListenerContainer() {
        SimpleMessageListenerContainer simpleMessageListenerContainer = new SimpleMessageListenerContainer();
        simpleMessageListenerContainer.setQueues(myQueue());
        simpleMessageListenerContainer.setConnectionFactory(connectionFactory());
        simpleMessageListenerContainer.setMessageListener(iotMessage);

        return simpleMessageListenerContainer;
    }
}

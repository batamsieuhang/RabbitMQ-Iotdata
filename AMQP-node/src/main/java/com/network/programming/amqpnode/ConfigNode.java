package com.network.programming.amqpnode;


import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.MessageListenerContainer;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

@Configuration
public class ConfigNode {



    @Autowired
    private IOTMessage iotMessage;

    // Define RabbitTemplate bean
    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        // Additional configuration if needed
        return rabbitTemplate;
    }


    @Bean
    public Queue myQueue() {
        return new Queue("IOTController", true);
    }

    @Bean
    public Exchange iotExchange() {
        return ExchangeBuilder.topicExchange("IOTController").durable(true).build();
    }

    @Bean
    public Binding iotBinding() {
        return BindingBuilder.bind(myQueue())
                .to(iotExchange())
                .with("IOTController")
                .noargs();
    }

    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory("localhost");
        cachingConnectionFactory.setUsername("guest");
        cachingConnectionFactory.setPassword("guest");
        return cachingConnectionFactory;
    }

    @Bean
    public MessageListenerContainer messageListenerContainer() {
        SimpleMessageListenerContainer simpleMessageListenerContainer = new SimpleMessageListenerContainer();
        simpleMessageListenerContainer.setQueues(myQueue());
        simpleMessageListenerContainer.setConnectionFactory(connectionFactory());
        simpleMessageListenerContainer.setMessageListener(iotMessage);

        return simpleMessageListenerContainer;
    }
}

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
    private static final String My_Queue2 = "IOTQueue2";
    private static final String My_Queue3 = "IOTQueue3";
    private static final String My_Queue4= "IOTQueue4";
    private static final String My_Queue5 = "IOTQueue5";
    private static final String My_Queue6 = "IOTQueue6";
    private static final String My_Queue7 = "IOTQueue7";
    private static final String My_Queue8 = "IOTQueue8";
    private static final String My_Queue9 = "IOTQueue9";
    private static final String My_Queue10 = "IOTQueue10";

    @Bean
    Queue myQueue() {
        return new Queue(My_Queue,true);
    }

    @Bean
    Queue myQueue2() {
        return new Queue(My_Queue2,true);
    }

    @Bean
    Queue myQueue3() {
        return new Queue(My_Queue3,true);
    }



    @Bean
    Queue myQueue4() {
        return new Queue(My_Queue4,true);
    }

    @Bean
    Queue myQueue5() {
        return new Queue(My_Queue5,true);
    }

    @Bean
    Queue myQueue6() {
        return new Queue(My_Queue6,true);
    }

    @Bean
    Queue myQueue7() {
        return new Queue(My_Queue7,true);
    }

    @Bean
    Queue myQueue8() {
        return new Queue(My_Queue8,true);
    }

    @Bean
    Queue myQueue9() {
        return new Queue(My_Queue9,true);
    }

    @Bean
    Queue myQueue10() {
        return new Queue(My_Queue10,true);
    }

    @Bean
    Exchange iotExchange() {
        return ExchangeBuilder.topicExchange("IOT_data").durable(true).build();
    }

    @Bean
    Binding iotBinding() {
        return BindingBuilder.bind(myQueue())
                .to(iotExchange())
                .with(My_Queue)
                .noargs();
    }


    @Bean
    Binding iotBinding2() {
        return BindingBuilder.bind(myQueue2())
                .to(iotExchange())
                .with(My_Queue2)
                .noargs();
    }

    @Bean
    Binding iotBinding3() {
        return BindingBuilder.bind(myQueue3())
                .to(iotExchange())
                .with(My_Queue3)
                .noargs();
    }

    @Bean
    Binding iotBinding4() {
        return BindingBuilder.bind(myQueue4())
                .to(iotExchange())
                .with(My_Queue4)
                .noargs();
    }


    @Bean
    Binding iotBinding5() {
        return BindingBuilder.bind(myQueue5())
                .to(iotExchange())
                .with(My_Queue5)
                .noargs();
    }


    @Bean
    Binding iotBinding6() {
        return BindingBuilder.bind(myQueue6())
                .to(iotExchange())
                .with(My_Queue6)
                .noargs();
    }


    @Bean
    Binding iotBinding7() {
        return BindingBuilder.bind(myQueue7())
                .to(iotExchange())
                .with(My_Queue7)
                .noargs();
    }


    @Bean
    Binding iotBinding8() {
        return BindingBuilder.bind(myQueue8())
                .to(iotExchange())
                .with(My_Queue8)
                .noargs();
    }


    @Bean
    Binding iotBinding9() {
        return BindingBuilder.bind(myQueue9())
                .to(iotExchange())
                .with(My_Queue9)
                .noargs();
    }


    @Bean
    Binding iotBinding10() {
        return BindingBuilder.bind(myQueue10())
                .to(iotExchange())
                .with(My_Queue10)
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



    @Bean
    MessageListenerContainer messageListenerContainer2() {
        SimpleMessageListenerContainer simpleMessageListenerContainer = new SimpleMessageListenerContainer();
        simpleMessageListenerContainer.setQueues(myQueue2());
        simpleMessageListenerContainer.setConnectionFactory(connectionFactory());
        simpleMessageListenerContainer.setMessageListener(iotMessage);

        return simpleMessageListenerContainer;
    }

    @Bean
    MessageListenerContainer messageListenerContainer3() {
        SimpleMessageListenerContainer simpleMessageListenerContainer = new SimpleMessageListenerContainer();
        simpleMessageListenerContainer.setQueues(myQueue3());
        simpleMessageListenerContainer.setConnectionFactory(connectionFactory());
        simpleMessageListenerContainer.setMessageListener(iotMessage);

        return simpleMessageListenerContainer;
    }


    @Bean
    MessageListenerContainer messageListenerContainer4() {
        SimpleMessageListenerContainer simpleMessageListenerContainer = new SimpleMessageListenerContainer();
        simpleMessageListenerContainer.setQueues(myQueue4());
        simpleMessageListenerContainer.setConnectionFactory(connectionFactory());
        simpleMessageListenerContainer.setMessageListener(iotMessage);

        return simpleMessageListenerContainer;
    }


    @Bean
    MessageListenerContainer messageListenerContainer5() {
        SimpleMessageListenerContainer simpleMessageListenerContainer = new SimpleMessageListenerContainer();
        simpleMessageListenerContainer.setQueues(myQueue5());
        simpleMessageListenerContainer.setConnectionFactory(connectionFactory());
        simpleMessageListenerContainer.setMessageListener(iotMessage);

        return simpleMessageListenerContainer;
    }


    @Bean
    MessageListenerContainer messageListenerContainer6() {
        SimpleMessageListenerContainer simpleMessageListenerContainer = new SimpleMessageListenerContainer();
        simpleMessageListenerContainer.setQueues(myQueue6());
        simpleMessageListenerContainer.setConnectionFactory(connectionFactory());
        simpleMessageListenerContainer.setMessageListener(iotMessage);

        return simpleMessageListenerContainer;
    }



    @Bean
    MessageListenerContainer messageListenerContainer7() {
        SimpleMessageListenerContainer simpleMessageListenerContainer = new SimpleMessageListenerContainer();
        simpleMessageListenerContainer.setQueues(myQueue7());
        simpleMessageListenerContainer.setConnectionFactory(connectionFactory());
        simpleMessageListenerContainer.setMessageListener(iotMessage);

        return simpleMessageListenerContainer;
    }


    @Bean
    MessageListenerContainer messageListenerContainer8() {
        SimpleMessageListenerContainer simpleMessageListenerContainer = new SimpleMessageListenerContainer();
        simpleMessageListenerContainer.setQueues(myQueue8());
        simpleMessageListenerContainer.setConnectionFactory(connectionFactory());
        simpleMessageListenerContainer.setMessageListener(iotMessage);

        return simpleMessageListenerContainer;
    }


    @Bean
    MessageListenerContainer messageListenerContainer9() {
        SimpleMessageListenerContainer simpleMessageListenerContainer = new SimpleMessageListenerContainer();
        simpleMessageListenerContainer.setQueues(myQueue9());
        simpleMessageListenerContainer.setConnectionFactory(connectionFactory());
        simpleMessageListenerContainer.setMessageListener(iotMessage);

        return simpleMessageListenerContainer;
    }


    @Bean
    MessageListenerContainer messageListenerContainer10() {
        SimpleMessageListenerContainer simpleMessageListenerContainer = new SimpleMessageListenerContainer();
        simpleMessageListenerContainer.setQueues(myQueue10());
        simpleMessageListenerContainer.setConnectionFactory(connectionFactory());
        simpleMessageListenerContainer.setMessageListener(iotMessage);

        return simpleMessageListenerContainer;
    }
}

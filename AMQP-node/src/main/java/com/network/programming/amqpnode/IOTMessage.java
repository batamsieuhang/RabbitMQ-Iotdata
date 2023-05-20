package com.network.programming.amqpnode;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.springframework.amqp.core.AcknowledgeMode;


import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.Node;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class IOTMessage implements MessageListener {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    private final ExecutorService executorService = Executors.newCachedThreadPool();

    @Override
    public void onMessage(Message message) {
        NodeCensor nodeCensor = new NodeCensor();
        String msg = new String(message.getBody());
        System.out.println(msg);
        if (msg.startsWith("Enable ")) {
            int plugId = Integer.parseInt(msg.substring(7));
            executorService.submit(() -> {
                try {
                    nodeCensor.processData(rabbitTemplate, plugId);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
        }
    }

    @Override
    public void containerAckMode(AcknowledgeMode mode) {
        MessageListener.super.containerAckMode(mode);
    }

    @Override
    public boolean isAsyncReplies() {
        return MessageListener.super.isAsyncReplies();
    }

    @Override
    public void onMessageBatch(List<Message> messages) {
        MessageListener.super.onMessageBatch(messages);
    }
}


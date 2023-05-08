package com.network.programming.amqpgateway;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;



@Service
public class IOTMessage implements MessageListener {


    @Autowired
    IotDataRepository myMongoRepository;
    @Override
    public void onMessage(Message message) {
        String msg = new String(message.getBody());
        System.out.println(msg);
        IotJava dataObject = parseSensorData(msg);

        // Save data object to MongoDB using repository
        myMongoRepository.save(dataObject);

    }

    private IotJava parseSensorData(String messageBody) {
        // Parse message data into a Java object using Gson
        Gson gson = new Gson();
        JsonObject dataObject = gson.fromJson(messageBody, JsonObject.class);

        IotJava sampleData = new IotJava(dataObject.get("unixTime").getAsLong(),dataObject.get("watValue").getAsDouble(),dataObject.get("workLoad").getAsInt(),dataObject.get("plugId").getAsInt(),dataObject.get("houseHoldId").getAsInt(),dataObject.get("houseId").getAsInt());

        return sampleData;
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

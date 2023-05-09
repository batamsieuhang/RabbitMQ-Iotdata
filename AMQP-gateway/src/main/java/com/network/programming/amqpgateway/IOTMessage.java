package com.network.programming.amqpgateway;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class IOTMessage implements MessageListener {


    @Autowired
    IotDataRepository myMongoRepository;
    @Override
    public void onMessage(Message message) {
        String msg = new String(message.getBody());
        System.out.println(msg);
        IotData dataObject = parseSensorData(msg);

        // Save data object to MongoDB using repository
        myMongoRepository.save(dataObject);

    }

    // function convert message to iot data
    private IotData parseSensorData(String messageBody) {
        // Parse message data into a Java object using Gson
        Gson gson = new Gson();
        JsonObject dataObject = gson.fromJson(messageBody, JsonObject.class);

        IotData sampleData = new IotData(dataObject.get("unixTime").getAsLong(),dataObject.get("watValue").getAsDouble(),dataObject.get("workLoad").getAsInt(),dataObject.get("plugId").getAsInt(),dataObject.get("houseHoldId").getAsInt(),dataObject.get("houseId").getAsInt());

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

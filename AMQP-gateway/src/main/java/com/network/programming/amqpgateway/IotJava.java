package com.network.programming.amqpgateway;


import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Document(collection = "iot_data")
public class IotJava {

    @MongoId
    private int id;

    @DateTimeFormat
    private Date unixTime;
    private float watValue;
    private int workLoad;
    private int plugId;
    private int houseHoldId;
    private int houseId;


}

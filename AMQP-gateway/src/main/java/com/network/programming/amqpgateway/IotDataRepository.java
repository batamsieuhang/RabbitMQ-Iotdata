package com.network.programming.amqpgateway;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface IotDataRepository extends MongoRepository<IotJava, Integer> {

}

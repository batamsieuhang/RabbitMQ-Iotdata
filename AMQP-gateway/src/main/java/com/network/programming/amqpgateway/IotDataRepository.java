package com.network.programming.amqpgateway;

import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableMongoRepositories
public interface IotDataRepository extends MongoRepository<IotJava, String> {


}

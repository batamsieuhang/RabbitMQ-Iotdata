package com.network.programming.amqpgateway;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableMongoRepositories
public interface IotDataRepository extends MongoRepository<IotData, String> {


}

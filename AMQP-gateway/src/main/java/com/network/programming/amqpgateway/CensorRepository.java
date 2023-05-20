package com.network.programming.amqpgateway;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CensorRepository extends MongoRepository<Censor, String> {
    Optional<Censor> findById(String id);
    List<Censor> findByNameAndStatus(int name, String status,String have_turn_on);
}

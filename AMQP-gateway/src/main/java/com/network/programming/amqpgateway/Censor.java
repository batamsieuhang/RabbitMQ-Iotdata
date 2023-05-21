package com.network.programming.amqpgateway;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "censor")
public class Censor{

    @Id
    String id;


    int name;
    String status;

    String have_turn_on;

    public String getHave_turn_on() {
        return have_turn_on;
    }

    public void setHave_turn_on(String have_turn_on) {
        this.have_turn_on = have_turn_on;
    }

    public Censor(String id, int name, String status, String have_turn_on) {
        this.id = ""+name;
        this.name = name;
        this.status = "on";
        this.have_turn_on = "no";
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getName() {
        return name;
    }

    public void setName(int name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

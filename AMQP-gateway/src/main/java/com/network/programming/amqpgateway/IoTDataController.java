package com.network.programming.amqpgateway;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class IoTDataController {

    @Autowired
    IotDataRepository iotDataRepository;

    @GetMapping("/iotdata")
    public ResponseEntity findAllData() {
        return new ResponseEntity(iotDataRepository.findAll(), HttpStatusCode.valueOf(200));
    }

    @GetMapping("/iotdata/{plugId}")
    public ResponseEntity findDataByPlugId(@PathVariable int plugId) {
        List<IotData> data = iotDataRepository.findByPlugId(plugId);
        return new ResponseEntity(data, HttpStatusCode.valueOf(200));
    }
}
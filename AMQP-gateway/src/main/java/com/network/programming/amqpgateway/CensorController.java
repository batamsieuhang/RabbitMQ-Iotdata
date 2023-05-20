package com.network.programming.amqpgateway;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class CensorController {

    @Autowired
    private RabbitTemplate iotData;


    @Autowired
    CensorRepository censorRepository;

    @GetMapping("/censor")
    public ResponseEntity findAllCensors() {
        return new ResponseEntity(censorRepository.findAll(), HttpStatus.OK);
    }

    @PostMapping("/censor")
    public ResponseEntity createCensor(@RequestBody Censor censor) {
        Censor savedCensor = censorRepository.save(censor);
        return new ResponseEntity(savedCensor, HttpStatus.CREATED);
    }

    @PutMapping("/censor/{id}")
    public ResponseEntity updateCensor(@PathVariable String id, @RequestParam int name, @RequestParam String status) {
        Optional<Censor> censorOptional = censorRepository.findById(id);
        if (!censorOptional.isPresent()) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        Censor censor = censorOptional.get();
        censor.setName(name);
        censor.setStatus(status);
        Censor updatedCensor = censorRepository.save(censor);
        return new ResponseEntity(updatedCensor, HttpStatus.OK);
    }




    @PostMapping("/check")
    public void checkAndSend(@RequestBody Censor requestCensor) {
        // Retrieve entries from the database where status is "on" and have_turn_on is "no"
        Iterable<Censor> statusCensors = censorRepository.findByNameAndStatus(requestCensor.getName(), "on", "no");

        for (Censor censor : statusCensors) {
            int plugId = censor.getName(); // Assuming name field in Censor corresponds to plugId
            String msg = "Enable " + plugId;

            // Send message to IOTController
            iotData.convertAndSend("IOTController", "IOTController", msg);

            // Update have_turn_on field to "yes"
            censor.setHave_turn_on("yes");
            censorRepository.save(censor);
        }
    }
}

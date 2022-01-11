package com.techprimers.kafka.springbootkafkaproducerexample.controller;

import com.techprimers.kafka.springbootkafkaproducerexample.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("kafka")
public class UserController {

    @Autowired
    private KafkaTemplate<String, User> kafkaTemplate;

    private static final String TOPIC = "Kafka_Example";

    @PostMapping("/post/{name}")
    public String postName(@PathVariable("name") final String name) {

        kafkaTemplate.send(TOPIC, new User(name, "n/a", 1L));

        return "Published successfully";
    }


    @PostMapping("/post/user")
    public String postDetails(@RequestBody User newUser) {

        kafkaTemplate.send(TOPIC,newUser);

        return "Published successfully";
    }

}

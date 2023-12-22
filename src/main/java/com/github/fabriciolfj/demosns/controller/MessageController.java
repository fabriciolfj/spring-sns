package com.github.fabriciolfj.demosns.controller;

import com.github.fabriciolfj.demosns.service.ProducerServiceEvents;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/message")
public class MessageController {

    private final ProducerServiceEvents producer;

    public MessageController(final ProducerServiceEvents producer) {
        this.producer = producer;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void sendMessage(@RequestBody final String value) {
        this.producer.send(value);
    }
}
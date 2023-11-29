package com.example.messageproducer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TicketController {
    @Autowired
    TicketService ticketService;

    @Autowired
    KafkaTemplate<String,String> kafkaTemplate;

    String topic = "topic1";

    @GetMapping("/")
    public String getDefaultString() {
        return "default string";
    }

    @GetMapping("/tickets")
    public List<Ticket> getAllTickets() {
        return ticketService.getAllTickets();
    }

    @GetMapping("/send/{message}")
    public String sendMessage(@PathVariable("message") String message) {
//        rabbitTemplate.convertAndSend(binding.getExchange(), binding.getRoutingKey(), message);
        kafkaTemplate.send(topic, message);
        System.out.println("message sent");
        return "sent";
    }
}

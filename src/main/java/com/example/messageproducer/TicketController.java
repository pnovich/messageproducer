package com.example.messageproducer;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TicketController {
    @Autowired
    TicketService ticketService;

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Autowired
    Binding binding;

    @GetMapping("/")
    public String getDefaultString() {
        return "default string";
    }

    @GetMapping("/tickets")
    public List<Ticket> getAllTickets() {
        return ticketService.getAllTickets();
    }

    @GetMapping("/send/{message}")
    public String sendMessage(@PathVariable ("message") String message) {
        rabbitTemplate.convertAndSend(binding.getExchange(), binding.getRoutingKey(), message);
        return "sent";
    }
}

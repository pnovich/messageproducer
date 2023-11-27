package com.example.messageproducer;

import jakarta.annotation.PostConstruct;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MessageReceiver {

    @Autowired
    Queue queue;

    @Autowired
    TicketService ticketService;

    @PostConstruct
    public void initMethod() {
        System.out.println("listener initialized");
    }

    @RabbitListener(queues = "#{queue.getName()}")
    public void handleMessage(String message) {
        System.out.println("message received");
        ticketService.saveTicket(message);
    }
}

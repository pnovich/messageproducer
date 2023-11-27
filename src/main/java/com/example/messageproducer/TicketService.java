package com.example.messageproducer;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class TicketService {
    @Autowired
    TicketRepository ticketRepository;

    @PostConstruct
    public void initDB() {
        System.out.println("starting initialization");
        Ticket ticket1 = new Ticket();
        ticket1.setName("ticket1");

        Ticket ticket2 = new Ticket();
        ticket2.setName("ticket2");

        Ticket ticket3 = new Ticket();
        ticket3.setName("ticket3");

        ticketRepository.save(ticket1);
        ticketRepository.save(ticket2);
        ticketRepository.save(ticket3);

        System.out.println("initialization closed");


//        List<Ticket> tickets = Arrays.asList(new Ticket("ticket1"), new Ticket("ticket2"), new Ticket("ticket3"));

//        ticketRepository.saveAll(tickets);
    }

    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }

    public void saveTicket(String name) {
        Ticket ticket = new Ticket();
        ticket.setName(name);
        ticketRepository.save(ticket);
    }
}

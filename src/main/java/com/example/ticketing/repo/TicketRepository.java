package com.example.ticketing.repo;

import com.example.ticketing.entity.Ticket;
import org.springframework.data.repository.CrudRepository;

public interface TicketRepository extends CrudRepository<Ticket,Long> {
}

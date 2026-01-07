package com.example.ticketing.ticketing;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TicketRepository extends JpaRepository<Ticket,Long> {
    Optional<Ticket> findBySeatNumber(Long seatNumber);
}

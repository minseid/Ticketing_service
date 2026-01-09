package com.example.ticketing.ticketing;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.Optional;

public interface TicketRepository extends JpaRepository<Ticket,Long> {
    Optional<Ticket> findBySeatNumber(Long seatNumber);
    boolean existsByRoundIdAndSeatNumberAndStatusIn(Long roundId, Long seatNumber, Collection<TicketStatus> status);
    long countByUserIdAndRoundIdAndStatusNot(Long userId, Long roundId, TicketStatus status);
    boolean existsByRoundIdAndSeatNumberAndStatusNot(Long userId,Long roundId, TicketStatus status);
}

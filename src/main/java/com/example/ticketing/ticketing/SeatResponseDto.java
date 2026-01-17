package com.example.ticketing.ticketing;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SeatResponseDto {

    private Long seatId;      // 좌석 고유 식별자
    private String roundId;  // 좌석 번호 (예: "A-12")
    private TicketStatus status;    // 좌석 상태 (AVAILABLE, LOCKED, OCCUPIED)
    
}
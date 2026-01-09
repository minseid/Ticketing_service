package com.example.ticketing.ticketing;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Time;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@Entity
@Getter@Setter
@NoArgsConstructor
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //가격 , 번호, 좌석번호 , <<예메자 정보>>
    private Long userId;
    private Long seatNumber; //좌석
    private Long roundId; // 회차 id (날짜 시간 )
    private String ticketNumber; // 예약 고유번호
    private Integer price;
    private Long performanceId ;

    @Enumerated(EnumType.STRING)
    private TicketStatus status;
    private LocalDateTime createdDate;


    public Ticket(Long userId, Long seatNumber, Long roundId, Integer price) {
        this.userId = userId;
        this.seatNumber = seatNumber;
        this.roundId = roundId;
        this.price = price;
        this.status = TicketStatus.PENDING; // 초기 생성 시 결제 대기 상태
        this.createdDate = LocalDateTime.now();
        this.ticketNumber = UUID.randomUUID().toString().substring(0, 8); // 예시용 고유번호
    }
}
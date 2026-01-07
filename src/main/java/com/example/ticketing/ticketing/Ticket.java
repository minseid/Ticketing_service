package com.example.ticketing.ticketing;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter@Setter
@NoArgsConstructor
public class Ticket {
    @Id @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;
    //가격 , 번호, 좌석번호 , <<예메자 정보>>
    private Long userId;
    private Long ticketNumber;
    private Long seatNumber;

    // userId 매핑

    public Ticket(Long userId, String name, String email, String password) {
        this.userId = userId;
    }
}
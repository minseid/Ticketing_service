package com.example.ticketing.service;


import com.example.ticketing.repo.TicketRepository;
import com.example.ticketing.repo.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TicketService {
    private final TicketRepository ticketRepository;
    private final UserRepository userRepository;

    /**
     * 1단계
     * 대기순서에 맞게 순번 지정 / 좌석 정리
     */
    public 


    /**
     * 2단계
     * 사용자가 좌석 선택 / 좌석 락
     */

    /**
     * 3단계
     * 사용자가 예약/ 일정시간 동안만 예약가능하게
     */

    /**
     * 4단계
     * 결제 / 결제시 좌석 마감처리 or 취소시 좌석 락 해제
     */
}

package com.example.ticketing.ticketing;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TicketService {


    private final TicketRepository ticketRepository;
    private final TicketVerificationService verificationService;


    /**
     * 1단계
     * 유저 검증
     * @
     */
    public void userVerification(Long userId, Long roundId) throws IllegalAccessException {
        verificationService.userLimitVerification(userId,roundId);
    }

    /**
     * 2단계
     * 사용자가 좌석 선택 / 좌석 락
     * CANCELLED를 빈 좌석과 동일하게
     */

    /**
     * 3단계
     * 사용자가 예약/ 일정시간 동안만 예약가능하게
     */


    /**
     * 4단계
     * 결제 수신 / 결제시 좌석 마감처리 or 취소시 좌석 락 해제
     */


    //티켓번호 생성
    private long generateTicketNumber(){
        return System.currentTimeMillis();
    }
}

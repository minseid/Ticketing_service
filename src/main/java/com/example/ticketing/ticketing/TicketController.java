package com.example.ticketing.ticketing;

import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/v1/api")
public class TicketController {
    private final TicketService ticketService;
    private static final Logger LOGGER = LogManager.getLogger(TicketController.class);


    /**
     *
     * @param userId
     * @return 대기순번
     */
    @RequestMapping("/reservation")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Long assignWaitingNumber(Long userId) {
        LOGGER.debug("Finding number of seats available for user {}", userId);
        return ticketService.assignWaitingNumber(userId);
    }

    /**
     *
     * @param userId
     * @param seatNumber
     * @return
     * @throws IllegalAccessException
     */
    @RequestMapping("/choise")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Boolean selectSeat(Long userId, Long seatNumber) throws IllegalAccessException {
        return ticketService.selectSeat(userId,seatNumber);
    }

    /**
     *
     * @param userId
     * @param seatNumber
     * @return
     * @throws IllegalAccessException
     */
    @RequestMapping("/reservation")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Ticket reserveTicket(Long userId,Long seatNumber) throws IllegalAccessException {
        return ticketService.reserveTicket(userId,seatNumber);
    }

    /**
     * 사용자 결제 / 결제시 마감 취소시 락해제
     */
    @RequestMapping("/payment")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public boolean complateTicket(Long ticketId){
        return ticketService.complateTicket(ticketId);
    }
    /**
     * 공연 예매 취소
     */


    /**
     * 공연 취소
     */

    /**
     * 예매내역 조회
     */
}

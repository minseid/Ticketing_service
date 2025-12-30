package com.example.ticketing.controller;

import com.example.ticketing.network.request.TicketRequest;
import com.example.ticketing.network.response.TicketResponse;
import com.example.ticketing.service.TicketService;
import com.example.ticketing.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/v1/api")
public class TicketController {
    private final TicketService ticketService;
    private final UserService userService;

    /**
     * 공연 예매
     */
    @PostMapping("/reservation")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public TicketResponse addTicket(@RequestBody TicketRequest request) {
        return ticketService.addTicket(request);
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

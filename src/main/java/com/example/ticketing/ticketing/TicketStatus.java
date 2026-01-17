package com.example.ticketing.ticketing;

public enum TicketStatus {
    EMPTY, // 기본
    PENDING, //결제 대기(락상태)
    CONFIRMED, // 확정(결제완료)
    CANCELLED, //취소(결제 취소) -- 선점 가능상태
}

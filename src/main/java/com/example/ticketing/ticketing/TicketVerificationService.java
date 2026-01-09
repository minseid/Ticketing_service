package com.example.ticketing.ticketing;

import lombok.AllArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TicketVerificationService {
    private final TicketRepository ticketRepository;
    private final RedisTemplate<String, String > redisTemplate;
    private static final String LOCK_KEY_PREFIX = "seat_lock: ";



    public void seatDuplicationVerification(Long roundId,Long seatNumber) throws IllegalAccessException {
        boolean isAlreadyReserved = ticketRepository.existsByRoundIdAndSeatNumberAndStatusIn(roundId,seatNumber, List.of(TicketStatus.PENDING,TicketStatus.CANCELLED)
        );
        if(isAlreadyReserved){
            throw new IllegalAccessException("이미 예약된 좌석입니다.");
        }
    }
    public void userLimitVerification(Long userId, Long roundId) throws IllegalAccessException {
        long reservationCount = ticketRepository.countByUserIdAndRoundIdAndStatusNot(
                userId, roundId, TicketStatus.CANCELLED
        );
        if (reservationCount >= 4) {
            throw new IllegalAccessException("예약가능 좌석수가 초과하였습니다.");
        }
    }

    public void validateLock(Long roundId, Long seatNumber, Long userId) throws IllegalAccessException {
        String key = LOCK_KEY_PREFIX + roundId + ":" + seatNumber;
        String lockedUserId = redisTemplate.opsForValue().get(key);
        if(lockedUserId == null){
            throw new IllegalAccessException("예매가능 시간이 만료되었습니다.");
        }
    }

    //좌석 락

    //좌석락 해제


}

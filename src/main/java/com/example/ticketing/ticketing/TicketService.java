package com.example.ticketing.ticketing;


import com.example.ticketing.user.User;
import com.example.ticketing.user.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
@AllArgsConstructor
public class TicketService {


    private final TicketRepository ticketRepository;
    private final UserRepository userRepository;
    //좌석락 관리
    private final Map<Long,ReservationLock> seatLocks = new ConcurrentHashMap<>();
    //대기열 관리
    private final Map<Long,Long> waitingQueue = new ConcurrentHashMap<>();
    private Long queueCounter = 0L;

    /**
     * 1단계
     * 대기순서에 맞게 순번 지정 / 좌석 정리
     */
    public Long assignWaitingNumber(Long userId){
        if(waitingQueue.containsKey(userId)){
            return waitingQueue.get(userId);
        }
        //새 대기순번 부여
        synchronized (this){
            queueCounter++;
            waitingQueue.put(userId, queueCounter);
            return queueCounter;
        }
    }
    public List<Long> getAvailableSeats(){
        //예약된 좌석 확인
        List<Long> reservedSeats = ticketRepository.findAll()
                .stream()
                .map(Ticket::getSeatNumber)
                .toList();

        //예약좌석 제외
        return java.util.stream.LongStream.rangeClosed(1,100)
                .boxed()
                .filter(seat -> !reservedSeats.contains(seat))
                .filter(seat -> !seatLocks.containsKey(seat)|| isLockExpired(seat))
                .toList();
    }
    /**
     * 2단계
     * 사용자가 좌석 선택 / 좌석 락
     */
    @Transactional
    public boolean selectSeat(Long userId, Long seatNumber) throws IllegalAccessException {
        //대기열 확인
        if (!waitingQueue.containsKey(userId)){
            throw new IllegalAccessException("대기열에 등록되지 않았습니다.");
        }
        //좌석이 이미 예약되있는지 확인
        if(ticketRepository.findBySeatNumber(seatNumber).isPresent()){
            return false;
        }
        if(seatLocks.containsKey(seatNumber)&& !isLockExpired(seatNumber)){
            return false;
        }
        //좌석 락 설정
        seatLocks.put(seatNumber,new ReservationLock(seatNumber,LocalDateTime.now().plusMinutes(5)));
        return true;
    }
    /**
     * 3단계
     * 사용자가 예약/ 일정시간 동안만 예약가능하게
     */
    @Transactional
    public Ticket reserveTicket(Long userId, Long seatNumber) throws IllegalAccessException {
        ReservationLock lock = seatLocks.get(seatNumber);
        if (lock == null|| !lock.getUserId().equals(userId)){
            throw new IllegalAccessException("해당 좌석에 대한 예약권한이 없습니다.");
        }
        //락이 만료되었는지
        if (isLockExpired(seatNumber)){
            seatLocks.remove(seatNumber);
            throw new IllegalAccessException("예약시간이 만료되었습니다.");
        }
        User user = userRepository.findById(userId).orElseThrow(()-> new IllegalArgumentException("존재하지않는 유저입니다."));
        Ticket ticket = new Ticket();
        ticket.setUserId(userId);
        ticket.setSeatNumber(seatNumber);
        ticket.setTicketNumber(generateTicketNumber());
        return ticketRepository.save(ticket);
    }

    /**
     * 4단계
     * 결제 / 결제시 좌석 마감처리 or 취소시 좌석 락 해제
     */

    @Transactional
    public boolean complateTicket(Long ticketId){
        Ticket ticket = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 티켓입니다."));
        //좌석 락 해제
        seatLocks.remove(ticket.getSeatNumber());
        //대기열에서 제거
        waitingQueue.remove(ticketId);
        return true;
    }
    @Transactional
    public void cancelReservation(Long ticketId){
        Ticket ticket = ticketRepository.findById(ticketId)
                .orElseThrow(()-> new IllegalArgumentException("존재하지 않는 티켓입니다."));
        Long seatNumber = ticket.getSeatNumber();
        //티켓삭제
        ticketRepository.delete(ticket);
        //좌석 락 해제
        seatLocks.remove(seatNumber);
    }

    // 내부 클래스: 예약 락 정보
    @AllArgsConstructor
    @Getter
    private static class ReservationLock {
        private Long userId;
        private LocalDateTime expiryTime;
    }
    //메소드
    private boolean isLockExpired(Long seatNumber){
        ReservationLock lock = seatLocks.get(seatNumber);
        return lock != null && LocalDateTime.now().isAfter(lock.getExpiryTime());
    }
    //티켓번호 생성
    private long generateTicketNumber(){
        return System.currentTimeMillis();
    }
}

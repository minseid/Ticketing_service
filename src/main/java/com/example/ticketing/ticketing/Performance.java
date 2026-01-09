package com.example.ticketing.ticketing;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter@Setter
public class Performance {
    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long reservationCount;
    @Enumerated(EnumType.STRING)
    private PerformanceStatus status;
    private Date startDate;
    private Date endDate;
}

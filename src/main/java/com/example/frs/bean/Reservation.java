package com.example.frs.bean;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity(name="reservation")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Reservation {
    @Id
    @Column(name = "reservation_id")
    private String reservation_id;

    @Column(name = "user_id")
    private String user_id;

    @Column(name = "schedule_id")
    private String schedule_id;

    @Column(name = "reservation_type")
    private String reservation_type;

    @Column(name = "booking_date")
    private String booking_date;

    @Column(name = "journey_date")
    private String journey_date;

    @Column(name = "no_of_seats")
    private int no_of_seats;

    @Column(name="total_fare")
    private double total_fare;

    @Column(name="booking_status")
    private int booking_status;

}


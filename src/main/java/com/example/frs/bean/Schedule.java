package com.example.frs.bean;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "schedule")
@Getter
@Setter
@NoArgsConstructor


public class Schedule {
    @Id
    @Column(name = "schedule_id")
    private String schedule_id;

    @Column(name="flight_id")
    private String flight_id;

    @Column(name = "route_id")
    private String route_id ;

    @Column(name = "travel_duration")
    private int travel_duration;

    @Column(name="available_days")
    private String available_days;

    @Column(name = "departure_time")
    private String departure_time;
}

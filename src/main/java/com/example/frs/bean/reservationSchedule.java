package com.example.frs.bean;

import lombok.Value;

@Value
public class reservationSchedule {
    private Route route;
    private Schedule schedule;
    private Flight flight;
    private Reservation reservation;
}

package com.example.frs.bean;


import lombok.Value;

@Value
public class PassengerResservation {
    private Reservation reservation;
    private Passenger passenger;
}

package com.example.frs.bean;

import com.example.frs.bean.Passenger;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class Passengers {
    List<Passenger> passengers = new ArrayList<>();
     public void addPassenger(Passenger p){
        passengers.add(p);
    }
}

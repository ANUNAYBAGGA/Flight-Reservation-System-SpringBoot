package com.example.frs.bean;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "route")
@Getter
@Setter
@NoArgsConstructor
public class Route {
    @Id
    @Column(name="route_id")
    private String route_id;

    @Column(name="source")
    private String source;

    @Column(name = "destination")
    private String destination;

    @Column(name = "distance")
    private int distance;

    @Column(name = "fare")
    private double fare;
}

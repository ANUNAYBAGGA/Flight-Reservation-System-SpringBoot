package com.example.frs.bean;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name="card")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Card {
    @Id
    @Column(name = "card_no")
    private String card_no ;

    @Column(name = "cvv")
    private String cvv;

    @Column(name = "valid_to")
    private String valid_to;

    @Column(name = "credit_balance")
    private String credit_balance;

    @Column(name = "user_id")
    private String user_id;
}
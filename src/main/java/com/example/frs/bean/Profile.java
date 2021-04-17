package com.example.frs.bean;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity(name="profile")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Profile {
    @Id
    @Column(name = "user_id")
    private String user_id ;

    @Column(name = "first_name")
    private String first_name;

    @Column(name = "last_name")
    private String last_name;

    @Column(name = "date_of_birth")
    private String date_of_birth;

    @Column(name = "gender")
    private String gender ;

    @Column(name = "street")
    private String street ;

    @Column(name = "location")
    private String location ;

    @Column(name = "city")
    private String city ;

    @Column(name = "state")
    private String state ;

    @Column(name = "pincode")
    private String pincode ;

    @Column(name = "mobile_no")
    private String mobile_no;

    @Column(name = "email_id")
    private String email_id;

    @Column(name = "password")
    private String password ;
}

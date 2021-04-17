package com.example.frs.bean;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.bytebuddy.dynamic.loading.InjectionClassLoader;

import javax.persistence.*;

@Entity(name = "credential")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Credential {
    @Id
    @Column(name = "user_id")
    private String user_id;

    @Column(name = "password")
    private String password;

    @Column(name = "user_type")
    private String user_type;

    @Column(name = "login_status")
    private int login_status;
}


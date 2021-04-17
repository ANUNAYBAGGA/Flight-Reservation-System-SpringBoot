package com.example.frs.dao;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
@Getter
@Setter

public class PassengerDao {

    @Autowired
    private SessionFactory session;
}

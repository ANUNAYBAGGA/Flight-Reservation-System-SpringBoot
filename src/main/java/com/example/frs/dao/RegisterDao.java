package com.example.frs.dao;

import com.example.frs.bean.Credential;
import com.example.frs.bean.Profile;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
@Getter
@Setter

public class RegisterDao {
    @Autowired
    private SessionFactory sessionFactory;


    public void save(Profile profile){
        Session s = sessionFactory.openSession();
        if(profile != null)
        {
            profile.setUser_id(profile.getEmail_id());
            Credential credential = new Credential(profile.getUser_id(), profile.getPassword(), "C", 1);
            Transaction t = s.beginTransaction();
            s.save(profile);
            s.save(credential);
            t.commit();
            s.close();
        }
        else {
            System.out.println("profile bean is null");
            return;
        }

    }

}

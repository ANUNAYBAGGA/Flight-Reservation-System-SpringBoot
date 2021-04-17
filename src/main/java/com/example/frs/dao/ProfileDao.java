package com.example.frs.dao;

import com.example.frs.bean.Profile;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;


@Repository
@Transactional
@Getter
@Setter

public class ProfileDao {
    @Autowired
    private SessionFactory sessionFactory;

    public List<Profile> list() {
        Session session = sessionFactory.openSession();
        List<Profile> users=new ArrayList<Profile>();
        try
        {
            String sql_query="from profile";
            users=session.createQuery(sql_query).list();
            return users;
        }
        catch(RuntimeException e)
        {
            return users;
        }
    }


    public Profile getByUserId(String userID) {
        Session session = sessionFactory.openSession();
        Profile Users;
        try
        {
            String sql_query="from profile WHERE user_id = :userID";
            Query query = session.createQuery(sql_query);
            query.setParameter("userID", userID);
            Object obj = query.getSingleResult();
            if(obj == null) return null;
            return (Profile)obj;
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
            return  null;
        }

    }

    public Profile getByEmailIdAndPassword(String emailID, String password) {
        Session session = sessionFactory.openSession();
        Profile Users;
        try
        {
            String sql_query="select p from profile p WHERE p.email_id = :emailID and p.password = :password";
            Query query = session.createQuery(sql_query);
            query.setParameter("emailID", emailID);
            query.setParameter("password", password);
            Object obj = query.getSingleResult();
            if(obj == null) return null;
            return (Profile)obj;
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }


}

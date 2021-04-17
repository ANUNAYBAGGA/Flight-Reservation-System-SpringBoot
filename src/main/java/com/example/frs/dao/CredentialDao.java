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

@Repository
@Transactional
@Getter
@Setter
public class CredentialDao {
    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private EntityManager em;

    public Credential getByUserId(String userID, String password) {
        Session session = sessionFactory.openSession();
        try
        {
            String sql_query="from credential WHERE user_id= :userID and password = :password";
            Query query = session.createQuery(sql_query);
            query.setParameter("password", password);
            query.setParameter("userID", userID);
            Object obj = query.getSingleResult();
            if(obj == null) return null;
            return (Credential)obj;
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
            return  null;
        }
    }

    public void changeLoginStatus0(String userID) {
        Session session = sessionFactory.openSession();
        try
        {
            Transaction t = session.beginTransaction();
            String sql_query="update credential c set c.login_status = 0 where c.user_id= :userID";
            Query query = session.createQuery(sql_query);
            query.setParameter("userID", userID);
            query.executeUpdate();
            t.commit();
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
            return;
        }
    }


    public void changeLoginStatus1(String userID) {
        Session session = sessionFactory.openSession();
        try
        {
            Transaction t = session.beginTransaction();
            String sql_query="update credential c set c.login_status = 1 where c.user_id= :userID";
            Query query = session.createQuery(sql_query);
            query.setParameter("userID", userID);
            query.executeUpdate();
            t.commit();
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
            return;
        }
    }

}

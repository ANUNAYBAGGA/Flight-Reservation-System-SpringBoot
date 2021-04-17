package com.example.frs.dao;


import com.example.frs.bean.Card;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Repository
@Transactional
@Getter
@Setter

public class CardDao {
    @Autowired
    private SessionFactory sessionFactory;


    public Card getCardNo(String userID) {
        Session session = sessionFactory.openSession();
        Card Users;
        try
        {
            String sql_query="from card WHERE user_id = :userID";
            Query query = session.createQuery(sql_query);
            query.setParameter("userID", userID);
            Object obj = query.getSingleResult();
            if(obj == null) return null;
            return (Card) obj;
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
            return  null;
        }
    }

}

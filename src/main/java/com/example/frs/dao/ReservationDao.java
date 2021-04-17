package com.example.frs.dao;

import com.example.frs.bean.Flight;
import com.example.frs.bean.Reservation;
import com.example.frs.bean.Schedule;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
@Getter
@Setter

public class ReservationDao {
    @Autowired
    private SessionFactory sessionFactory;

    public void delete(String reservationID) {
        Session session = sessionFactory.openSession();
        try {
            Transaction t = session.beginTransaction();
            Query delete_query2 = session.createQuery("delete from reservation where reservation_id = :id");
            delete_query2.setParameter("id", reservationID);
            delete_query2.executeUpdate();
            t.commit();

        } catch (Exception e) {
            System.out.println("There is some delete exception"+" "+e);
            return;
        }
    }

    /* @Autowired
    private EntityManager em;

    public List<Reservation> list() {
        Session session = sessionFactory.openSession();
        List<Reservation> users=new ArrayList<Reservation>();
        try
        {
            String sql_query="from reservation";
            users = session.createQuery(sql_query).list();
            return users;
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public Reservation get(String reservationID) {
        System.out.println("In get reservation DAO " + reservationID );
        Session session = sessionFactory.openSession();
        Reservation fl=new Reservation();
        try
        {
            fl =  (Reservation) session.get(Reservation.class, reservationID);
            System.out.println(fl.getReservation_id());
            return fl;
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
            return null;
        }
    }*/

}

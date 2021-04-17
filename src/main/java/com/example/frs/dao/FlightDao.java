package com.example.frs.dao;

import com.example.frs.bean.Flight;
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

public class FlightDao {
    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private EntityManager em;

    public List<Flight> list() {
        Session session = sessionFactory.openSession();
        List<Flight> users=new ArrayList<Flight>();
        try
        {
            String sql_query="from flight";
            users = session.createQuery(sql_query).list();
            return users;
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public Flight get(String flightID) {
        Session session = sessionFactory.openSession();
        Flight fl=new Flight();
        try
        {
            fl =  (Flight) session.get(Flight.class, flightID);
            return fl;
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public void save(Flight flight) {
        Session session = sessionFactory.openSession();
        if(flight != null)
        {
            Transaction t = session.beginTransaction();
            session.save(flight);
            t.commit();
            session.close();
        }
        else{
            System.out.println("flight bean is null");
            return;
        }

    }


    public void update(Flight flight) {
        Session session = sessionFactory.openSession();
        if(flight != null)
        {
            Transaction t = session.beginTransaction();
            session.update(flight);
            t.commit();
            session.close();
        }
        else{
            System.out.println("flight bean is null");
            return;
        }
    }


    public void delete(String flightID) {
        Session session = sessionFactory.openSession();
        try {
            Transaction t = session.beginTransaction();
            Query delete_query = session.createQuery("delete from schedule where flight_id = :id");
            delete_query.setParameter("id", flightID);
            delete_query.executeUpdate();

            Query delete_query2 = session.createQuery("delete from flight where flight_id = :id");
            delete_query2.setParameter("id", flightID);
            delete_query2.executeUpdate();
            t.commit();

        } catch (Exception e) {
            System.out.println("There is some delete exception");
            return;
        }
    }
}

package com.example.frs.dao;

import com.example.frs.bean.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Repository
@Transactional
@Getter
@Setter

public class CustomerDao {
    @Autowired
    private SessionFactory sessionFactory;



    public void reserveTicket(Reservation reservation)
    {
        try {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            session.save(reservation);
            transaction.commit();

        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void savePassenger(List<Passenger> passengers)
    {
        try {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            for (Passenger p : passengers) {
                session.save(p);
            }
            transaction.commit();


        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public List<reservationSchedule> viewBookedTicket(){
        try {
            Session session = sessionFactory.openSession();
            String query = "select new com.example.frs.bean.reservationSchedule(r, s, f, rser) from route r inner join schedule s on r.route_id = s.route_id inner join flight f on  f.flight_id = s.flight_id inner join reservation rser on s.schedule_id = rser.schedule_id";
            Query q = session.createQuery(query);
            return q.list();
        } catch(Exception e){
            System.out.println(e.getMessage());
            return new ArrayList<>();
        }
    }

    public List<PassengerResservation> viewTicket(String reservationId)
    {
        try {
            Session session = sessionFactory.openSession();
            String squery = "select new com.example.frs.bean.PassengerResservation(r, p) from reservation r inner join passenger p on r.reservation_id = p.reservation_id where r.reservation_id = :reservationId";
            Query q = session.createQuery(squery, PassengerResservation.class);
            q.setParameter("reservationId", reservationId);
            return q.list();
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            return new ArrayList<>();
        }
    }

    public void deleteBookedTicket(String reservationID) {
        Session session = sessionFactory.openSession();
        System.out.println(reservationID);
        try {

            Query delete_query = session.createQuery("delete from reservation where reservation_id = :id");
            delete_query.setParameter("id", reservationID);
            Transaction t = session.beginTransaction();
            delete_query.executeUpdate();
            t.commit();

        } catch (Exception e) {
            System.out.println("There is some delete exception " + e);
            return;
        }
    }

    public List<RSF> getSchedule(String source, String destination, String date)
    {
        String[] DateChunk = date.split("-");
        LocalDate localDate = LocalDate.of(Integer.parseInt(DateChunk[0]), Integer.parseInt(DateChunk[1]), Integer.parseInt(DateChunk[2]));
        java.time.DayOfWeek dayOfWeek = localDate.getDayOfWeek();
        String day = dayOfWeek.toString().toLowerCase().trim();

        try {
            Session session = sessionFactory.openSession();
            //String query = "select s from schedule s inner join route r on s.route_id = r.routeId where r.source = :source and r.destination = :destination and s.available_days = :days";
            String query = "select new com.example.frs.bean.RSF(r, s, f) from route r inner join schedule s on r.route_id = s.route_id inner join flight f on f.flight_id = s.flight_id where r.source = :source and r.destination = :destination and s.available_days = :days";
            Query q = session.createQuery(query);
            q.setParameter("source", source);
            q.setParameter("destination", destination);
            q.setParameter("days", day);

            return q.list();
        } catch(Exception e){
            System.out.println(e.getMessage());
            return new ArrayList<>();
        }
    }
}

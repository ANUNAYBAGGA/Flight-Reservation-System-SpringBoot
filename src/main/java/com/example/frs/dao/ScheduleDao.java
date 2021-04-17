package com.example.frs.dao;

import com.example.frs.bean.Schedule;
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

public class ScheduleDao {
    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private EntityManager em;

    
    public List<Schedule> list() {
        Session session = sessionFactory.openSession();
        List<Schedule> routes =new ArrayList<Schedule>();
        try
        {
            String sql_query="from schedule";
            routes = session.createQuery(sql_query).list();
            return routes;
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public Schedule get(String scheduleID) {
        Session session = sessionFactory.openSession();
        Schedule sc = new Schedule();
        try
        {
            sc =  (Schedule) session.get(Schedule.class, scheduleID);
            return sc;
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public void save(Schedule schedule) {
        Session session = sessionFactory.openSession();
        if(schedule != null)
        {
            Transaction t = session.beginTransaction();
            session.save(schedule);
            t.commit();
            session.close();
        }
        else{
            System.out.println("schedule bean is null");
            return;
        }

    }


    public void update(Schedule schedule) {
        Session session = sessionFactory.openSession();
        if(schedule != null)
        {
            Transaction t = session.beginTransaction();
            session.update(schedule);
            t.commit();
            session.close();
        }
        else{
            System.out.println("schedule bean is null");
            return;
        }
    }


    public void delete(String scheduleID) {
        Session session = sessionFactory.openSession();
        try {
            Transaction t = session.beginTransaction();

            Query delete_query = session.createQuery("delete from schedule where schedule_id = :id");
            delete_query.setParameter("id", scheduleID);
            delete_query.executeUpdate();

            t.commit();

        } catch (Exception e) {
            System.out.println("There is some delete exception");
            return;
        }
    }
    
    
}

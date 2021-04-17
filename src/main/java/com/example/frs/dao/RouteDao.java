package com.example.frs.dao;

import com.example.frs.bean.Flight;
import com.example.frs.bean.Route;
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

public class RouteDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private EntityManager em;

    public List<Route> list() {
        Session session = sessionFactory.openSession();
        List<Route> routes =new ArrayList<Route>();
        try
        {
            String sql_query="from route";
            routes = session.createQuery(sql_query).list();
            return routes;
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public List<Route> get(String routeID) {
        Session session = sessionFactory.openSession();
        List<Route> routes = new ArrayList<Route>();
        try
        {
            String sql_query="from flight WHERE route_id="+routeID;
            routes = session.createQuery(sql_query).list();
            return routes;
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public void save(Route route) {
        Session session = sessionFactory.openSession();
        if(route != null)
        {
            Transaction t = session.beginTransaction();
            session.save(route);
            t.commit();
            session.close();
        }
        else{
            System.out.println("route bean is null");
            return;
        }

    }


    public void update(Route route) {
        Session session = sessionFactory.openSession();
        if(route != null)
        {
            Transaction t = session.beginTransaction();
            session.update(route);
            t.commit();
            session.close();
        }
        else{
            System.out.println("route bean is null");
            return;
        }
    }


    public void delete(String routeID) {
        Session session = sessionFactory.openSession();
        try {
            Transaction t = session.beginTransaction();

            Query delete_query = session.createQuery("delete from schedule where route_id = :id");
            delete_query.setParameter("id", routeID);
            delete_query.executeUpdate();

            Query delete_query2 = session.createQuery("delete from route where route_id = :id");
            delete_query2.setParameter("id", routeID);
            delete_query2.executeUpdate();
            t.commit();

        } catch (Exception e) {
            System.out.println("There is some delete exception");
            return;
        }
    }
}

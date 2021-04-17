package com.example.frs.service;

import com.example.frs.bean.Flight;
import com.example.frs.dao.FlightDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class FlightController {

    @Autowired
    private FlightDao flightDao;

    @GetMapping("/addFlight")
    public String render_add_flight(Model model){
        model.addAttribute("flight", new Flight());
        return "addFlight_new";
    }
    @PostMapping("/addFlight")
    public String add_flight(Model model, @ModelAttribute("flight") Flight flight){
        flightDao.save(flight);
        model.addAttribute("flight", new Flight());
        return "redirect:/addFlight";
    }

    
    @GetMapping("/manageFlight")
    public String render_manage_flight(Model model){
        List<Flight> flights = flightDao.list();
        model.addAttribute("flights", flights);
        return "manageFlight_new";
    }   
    
    @GetMapping("/updateFlight")
    public String render_update_flight(Model model){
        model.addAttribute("flight", new Flight());
        return "updateFlight_new";
    }
    
    @PostMapping("/updateFlight")
    public String update_flight(Model model, @ModelAttribute("flight") Flight flight){
        flightDao.update(flight);
        List<Flight> flights = flightDao.list();
        model.addAttribute("flights", flights);
        return "redirect:/updateFlight";
    }
    
    
    @GetMapping("/deleteFlight")
    public String render_delete_flight(Model model, @ModelAttribute("flight") Flight flight){
    	flightDao.delete(flight.getFlight_id());
        List<Flight> flights = flightDao.list();
        model.addAttribute("flights", flights);
        return "manageFlight_new";
    }
}

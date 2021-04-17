package com.example.frs.service;

import com.example.frs.bean.Flight;
import com.example.frs.bean.Route;
import com.example.frs.bean.Schedule;
import com.example.frs.dao.FlightDao;
import com.example.frs.dao.RouteDao;
import com.example.frs.dao.ScheduleDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ScheduleController {

    @Autowired
    private ScheduleDao scheduleDao;

    @Autowired
    private FlightDao flightDao;

    @Autowired
    private RouteDao routeDao;



    @GetMapping("/addSchedule")
    public String render_schedule(Model model){
        model.addAttribute("schedule", new Schedule());
        List<Route> routes = routeDao.list();
        List<Flight> flights = flightDao.list();
        model.addAttribute("routes", routes);
        model.addAttribute("flights",flights);
        return "addSchedule_new";
    }

    @PostMapping("/addSchedule")
    public String add_schedule(Model model, @ModelAttribute("schedule") Schedule schedule){
        scheduleDao.save(schedule);
        List<Route> routes = routeDao.list();
        List<Flight> flights = flightDao.list();
        model.addAttribute("routes", routes);
        model.addAttribute("flights",flights);
        model.addAttribute("schedule", new Schedule());
        return "redirect:/addSchedule";
    }

    @GetMapping("/manageSchedule")
    public String render_manage_schedule(Model model){
        List<Schedule> schedules = scheduleDao.list();
        model.addAttribute("schedules", schedules);
        return "manageSchedule_new";
    }
    @PostMapping("/manageSchedule")
    public String manage_schedule(Model model, @ModelAttribute("schedule") Schedule schedule){
        scheduleDao.save(schedule);
        model.addAttribute("schedules", new Route());
        return "redirect:/manageSchedule";
    }

    @GetMapping("/updateSchedule")
    public String render_update_schedule(Model model){
        model.addAttribute("schedule", new Schedule());
        List<Route> routes = routeDao.list();
        List<Flight> flights = flightDao.list();
        model.addAttribute("routes", routes);
        model.addAttribute("flights",flights);
        return "updateSchedule_new";
    }

    @PostMapping("/updateSchedule")
    public String update_schedule(Model model, @ModelAttribute("schedule") Schedule schedule){
        scheduleDao.update(schedule);
        List<Route> routes = routeDao.list();
        List<Flight> flights = flightDao.list();
        model.addAttribute("routes", routes);
        model.addAttribute("flights",flights);
        return "redirect:/updateSchedule";
    }


    @GetMapping("/deleteSchedule")
    public String render_delete_schedule(Model model,@ModelAttribute("schedule") Schedule schedule){
        scheduleDao.delete(schedule.getSchedule_id());
        List<Schedule> schedules = scheduleDao.list();
        model.addAttribute("schedules", schedules);
        return "manageSchedule_new";
    }

}

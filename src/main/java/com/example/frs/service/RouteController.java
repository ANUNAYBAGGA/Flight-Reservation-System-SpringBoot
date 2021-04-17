package com.example.frs.service;

import com.example.frs.bean.Flight;
import com.example.frs.bean.Route;
import com.example.frs.dao.FlightDao;
import com.example.frs.dao.RouteDao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RouteController {

    @Autowired
    private RouteDao routeDao;

    @GetMapping("/addRoute")
    public String render_route(Model model){
        model.addAttribute("route", new Route());
        return "addRoute_new";
    }

    @PostMapping("/addRoute")
    public String add_route(Model model, @ModelAttribute("route") Route route){
        routeDao.save(route);
        model.addAttribute("route", new Route());
        return "redirect:/addRoute";
    }

    @GetMapping("/manageRoute")
    public String render_manage_route(Model model){
        List<Route> routes = routeDao.list();
        model.addAttribute("routes", routes);
        return "manageRoute_new";
    }

    @PostMapping("/manageRoute")
    public String manage_route(Model model, @ModelAttribute("route") Route route){
        routeDao.save(route);
        model.addAttribute("route", new Route());
        return "redirect:/manageRoute";
    }


    @GetMapping("/updateRoute")
    public String render_update_route(Model model){
        model.addAttribute("route", new Route());
        return "updateRoute_new";
    }

    @PostMapping("/updateRoute")
    public String update_route(Model model, @ModelAttribute("route") Route route){
        routeDao.update(route);
        List<Route> routes = routeDao.list();
        model.addAttribute("routes", routes);
        return "redirect:/updateRoute";
    }

    @GetMapping("/deleteRoute")
    public String render_delete_route(Model model,@ModelAttribute("route") Route route){
        routeDao.delete(route.getRoute_id());
        List<Route> routes = routeDao.list();
        model.addAttribute("routes", routes);
        return "manageRoute_new";
    }

}

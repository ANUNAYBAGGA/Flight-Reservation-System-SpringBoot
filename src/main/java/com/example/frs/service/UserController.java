package com.example.frs.service;
import java.util.List;
import java.util.Random;

import com.example.frs.bean.*;
import com.example.frs.dao.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {
    @Autowired
    private ProfileDao profileDao;
    @GetMapping("/userdata")
    public String render_userdata(Model model){
        List<Profile> profiles = profileDao.list();
        model.addAttribute("profiles", profiles);
        return "userdata_new";
    }

}

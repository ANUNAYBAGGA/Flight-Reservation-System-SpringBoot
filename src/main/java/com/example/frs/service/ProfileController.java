package com.example.frs.service;



import com.example.frs.bean.Credential;
import com.example.frs.bean.Profile;
import com.example.frs.dao.CredentialDao;
import com.example.frs.dao.ProfileDao;
import com.example.frs.dao.RegisterDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ProfileController {
    @Autowired
    private RegisterDao registerDao;

    @GetMapping("/signup")
    public String render_signup(Model model){
        model.addAttribute("profile", new Profile());
        return "signup";
    }

    @PostMapping("/signup")
    public String check_signup(@ModelAttribute("profile")Profile profile){
        registerDao.save(profile);
        return "login";
    }
}



package com.example.frs.service;

import com.example.frs.bean.Credential;
import com.example.frs.bean.Profile;
import com.example.frs.dao.CredentialDao;
import com.example.frs.dao.ProfileDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class HomeController {
    @Autowired
    ProfileDao profileDao;

    @Autowired
    CredentialDao credentialDao;

    @GetMapping("/userHome")
    public String render_userHome(Model model, HttpServletRequest request){
        String user_id = (String)request.getSession().getAttribute("SESSION_UID");
        Profile profile = profileDao.getByUserId(user_id);
        model.addAttribute("profile_firstName", profile.getFirst_name());
        model.addAttribute("profile_lastName", profile.getLast_name());
        return "userHome_new";
    }

    @GetMapping("/adminHome")
    public String render_adminHome(Model model, HttpServletRequest request){
        return "adminHome_new";
    }

    @GetMapping("/logout")
    public String render_logout(Model model, HttpServletRequest request){
        String userID = (String)request.getSession().getAttribute("SESSION_UID");
        HttpSession session = request.getSession(false);
        if(session!=null) {
            credentialDao.changeLoginStatus0(userID);
            session.invalidate();
        }

        return "redirect:/";
    }

}

package com.example.frs.service;

import com.example.frs.bean.Credential;
import com.example.frs.bean.Profile;
import com.example.frs.dao.CredentialDao;
import com.example.frs.dao.ProfileDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class CredentialController {
    @Autowired
    private CredentialDao credentialDao;
    @Autowired
    private ProfileDao profileDao;

    @GetMapping("/")
    public String render_login(Model model){
        return "login_new";
    }

    @PostMapping("/")
    public String check_login(Model model, @RequestParam("emailID") String emailId , @RequestParam("password") String password, HttpServletRequest request){

        Credential credential = credentialDao.getByUserId(emailId, password);

        if (credential == null){
            String reply = "You never registered or wrong password";
            model.addAttribute("reply", reply);
            return "login_new";
        }

        request.getSession().setAttribute("SESSION_UID", credential.getUser_id());
        String userID = (String)request.getSession().getAttribute("SESSION_UID");
        System.out.println(userID);
        credentialDao.changeLoginStatus1(userID);
        if("C".equals(credential.getUser_type())){
            return "redirect:/userHome";
        }

        return "redirect:/adminHome";
    }
}

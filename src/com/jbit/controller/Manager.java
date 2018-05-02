package com.jbit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * Created by Xc on 2018/4/19.
 */
@Controller
@RequestMapping("/my")
public class Manager {

    @RequestMapping("/backend")
    public String backend(){
        return "backendlogin";
    }
    @RequestMapping("/dev")
    public String dev(){
        return "devlogin";
    }
    @RequestMapping("/backend/main")
    public String developerMain(){
        return "backend/main";
    }
    @RequestMapping("/dev/main")
    public String backendMain(){
        return "developer/main";
    }
    @RequestMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/index.jsp";
    }
}

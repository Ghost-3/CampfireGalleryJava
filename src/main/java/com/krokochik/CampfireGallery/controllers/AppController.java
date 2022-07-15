package com.krokochik.CampfireGallery.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class AppController {

    public long id = 1;
    String topnav = "topnav";

    @GetMapping("/")
    public String main(Model model, HttpServletRequest request) {
        long changeID = 0;
        try {
            id = Integer.parseInt(request.getParameter("id"));
        }
        catch (NumberFormatException formatException){}
        try{
            changeID = Integer.parseInt(request.getParameter("changeId"));
            id = id + changeID;
        }
        catch(NumberFormatException numberFormatException){}

        if(id < 1)
            id = 1;

        System.out.println(id);

        model.addAttribute("id", id + "");
        model.addAttribute("topnav", topnav);

        if (changeID != 0) {
            return "redirect:?id=" + id;
        }
        else {
            return "main";
        }

    }

    @PostMapping("/")
    public String mainPost(Model model, HttpServletRequest request){
        return "redirect:?id=" + request.getParameter("input");
    }

    @GetMapping("/switchTopNav")
    public String switchGet(Model model, HttpServletRequest request){
        switch (topnav) {
            case "topnav" -> topnav = "topnav responsive";
            case "topnav responsive" -> topnav = "topnav";
        }
        model.addAttribute("topnav", topnav);
        return "redirect:?id=" + request.getParameter("input");
    }

}
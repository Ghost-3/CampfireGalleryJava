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

    @GetMapping("/")
    public String main(Model model, HttpServletRequest request) {
        try {
            id = Integer.parseInt(request.getParameter("id"));
        }
        catch (NumberFormatException formatException){}
        try{
            id = id + Integer.parseInt(request.getParameter("changeId"));
        }
        catch(NumberFormatException numberFormatException){}

        if(id < 1)
            id = 1;

        System.out.println(id);

        model.addAttribute("id", id + "");

        return "main";
    }

    @PostMapping("/")
    public String mainPost(Model model, HttpServletRequest request){
        return "redirect:?id=" + request.getParameter("input");
    }


}
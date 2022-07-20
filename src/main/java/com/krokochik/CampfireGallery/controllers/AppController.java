package com.krokochik.CampfireGallery.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.stream.IntStream;

@Controller
public class AppController {

    @GetMapping("/")
    public String main(Model model, HttpServletRequest request, HttpServletResponse response) {
        return "main";
    }

    @PostMapping("/")
    public String mainPost(Model model, HttpServletRequest request){
        return "main";
    }

}
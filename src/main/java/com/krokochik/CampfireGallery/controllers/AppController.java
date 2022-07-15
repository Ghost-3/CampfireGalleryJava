package com.krokochik.CampfireGallery.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
public class AppController {

    long globalId = 0, globalChangeID = 0;
    String topnav = "topnav";

    @GetMapping("/")
    public String main(Model model, @RequestParam(name = "id", required = false) String id, @RequestParam(name = "changeId", required = false) String changeId) {
        try {
            globalId = Long.parseLong(id);
        } catch (NumberFormatException ignored) {
            return "redirect:/?id=1";
        }

        try {
            globalChangeID = Long.parseLong(changeId);
            globalId = globalId + globalChangeID;
        } catch (NumberFormatException ignored) {}

        if (globalId < 1)
            globalId = 1;

        for(int i = 0; i < 10; i++){
            model.addAttribute("id" + i, id + "");
        }
        model.addAttribute("topnav", topnav);

        if (id.equals(globalId + "")) {
            return "main";
        } else {
            return "redirect:?id=" + globalId;
        }
    }

    @PostMapping("/")
    public String mainPost(Model model, HttpServletRequest request) {
        return "redirect:?id=" + request.getParameter("input");
    }

    @GetMapping("/switchTopNav")
    public String switchGet(Model model, HttpServletRequest request) {
        switch (topnav) {
            case "topnav" -> topnav = "topnav responsive";
            case "topnav responsive" -> topnav = "topnav";
        }

        model.addAttribute("topnav", topnav);
        return "redirect:?id=" + globalId;
    }


    @GetMapping({"/galleryImageControllerFactorySolutionStrategyPrinter", "/strategyPrinter", "/Printer", "/printer"})
    public String galleryImageControllerFactorySolutionStrategyPrinter1(Model model) {
        String[] img = {"https://sun9-32.userapi.com/c543101/v543101051/4e88e/ROU4N8eD22U.jpg",
                "http://www.semyaimir.ru/UserFiles/Image/zabavnye_istorii/zloy_printer.gif",
                "https://img2.joyreactor.cc/pics/post/принтер-Комиксы-Мемы-смешные-картинки-697290.jpeg",
                "https://admem.ru/content/images/1390958305.jpg"};
        int random = (int) (Math.random() * 4);

        model.addAttribute("printerImg", img[random]);
        model.addAttribute("width", random == 2 ? "25%" : "50%");

        return "printer";
    }

}

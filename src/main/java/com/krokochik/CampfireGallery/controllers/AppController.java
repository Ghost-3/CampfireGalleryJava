package com.krokochik.CampfireGallery.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.stream.IntStream;

@Controller
public class AppController {

    long globalId, globalChangeID;
    String topNav = "topNav";
    final String[] img = {"https://sun9-32.userapi.com/c543101/v543101051/4e88e/ROU4N8eD22U.jpg",
                          "https://img2.joyreactor.cc/pics/post/принтер-Комиксы-Мемы-смешные-картинки-697290.jpeg",
                          "https://admem.ru/content/images/1390958305.jpg"};

    @GetMapping("/")
    public String main(Model model, @RequestParam(name = "id", required = false) String id, @RequestParam(name = "changeId", required = false) String changeId) {
        try
        {
            globalId = Long.parseLong(id);
        }
        catch (NumberFormatException ignored) {
            return "redirect:/?id=1";
        }

        try
        {
            globalChangeID = Long.parseLong(changeId);
            globalId += globalChangeID;
        }
        catch (NumberFormatException ignored){}

        if (globalId < 1)
            return "redirect:/?id=1";

        IntStream.range(0, 11).forEach(i -> model.addAttribute("id" + i, id + ""));
        model.addAttribute("topNav", topNav);

        if (id.equals(globalId + ""))
            return "main";
        else
            return "redirect:?id=" + globalId;
    }

    @GetMapping("/extended")
    public String mainExtended(Model model, @RequestParam(name = "id", required = false) String id, @RequestParam(name = "changeId", required = false) String changeId) {
        try {
            globalId = Long.parseLong(id);
        } catch (NumberFormatException ignored) {
            return "redirect:/extended/?id=1";
        }

        try {
            globalChangeID = Long.parseLong(changeId);
            globalId += globalChangeID;
        } catch (NumberFormatException ignored) {}

        if (globalId < 1)
            return "redirect:/extended/?id=1";

        for(int i = 0; i < 10; i++){
            model.addAttribute("id" + i, globalId + "");
        }
        for(int i = 10; i < 16; i++){
            model.addAttribute("id" + i, globalId + (i - 10) + "");
        }
        model.addAttribute("topNav", topNav);

        if (id.equals(globalId + "")) {
            return "extendedMain";
        } else {
            return "redirect:/extended/?id=" + globalId;
        }
    }

    @PostMapping("/")
    public String mainPost(Model model, HttpServletRequest request) {
        return "redirect:?id=" + request.getParameter("input");
    }

    @PostMapping("/extended")
    public String extendedMainPost(Model model, HttpServletRequest request) {
        return "redirect:extended/?id=" + request.getParameter("input");
    }

    @GetMapping("/switchTopNav")
    public String switchGet(Model model, HttpServletRequest request) {
        switch (topNav) {
            case "topNav" -> topNav = "topNav responsive";
            case "topNav responsive" -> topNav = "topNav";
        }

        model.addAttribute("topNav", topNav);
        return "redirect:?id=" + globalId;
    }

    @GetMapping("/extended/switchTopNav")
    public String extendedSwitchGet(Model model, HttpServletRequest request) {
        switch (topNav) {
            case "topNav" -> topNav = "topNav responsive";
            case "topNav responsive" -> topNav = "topNav";
        }

        model.addAttribute("topNav", topNav);
        return "redirect:/extended/?id=" + globalId;
    }


    @GetMapping({"/galleryImageControllerFactorySolutionStrategyPrinter", "/strategyPrinter", "/Printer", "/printer"})
    public String strategyPrinter(Model model) {


        model.addAttribute("printerImg", img[(int) (Math.random() * 3)]);
        return "printer";
    }

}

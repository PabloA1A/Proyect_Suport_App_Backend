package dev.pablo.Project_Support_App_Backend.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping(path = "")
    public String index() {
        return "Hello SpringBoot";
    }
}

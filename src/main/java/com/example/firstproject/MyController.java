package com.example.firstproject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MyController {
    @GetMapping("/hello")
    public String greeting(Model model) {
        model.addAttribute("username","hyejeong");
        return "greetings";
    }
}


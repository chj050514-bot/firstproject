package com.example.firstproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FirstController {
    @GetMapping("/hello")
    public String greeting(Model model) {
        model.addAttribute("username",
                "hyejeong");
        return "greetings";
    }
    @GetMapping("/bye")
    public String SeeYouAgain(Model model){
        model.addAttribute("username",
            "hyejeong");
        return "bye";
    }

    @GetMapping("/random-quote")
    public String randomQuote(Model model){
        String[] quotes = {
                "피할수 없으면 즐겨라.",
                "절대 어제를 후회하지 마라. 인생은 오늘의 내 안에 있고 내일은 스스로 만드는것이다.",
                "계단을 밟아야 계단 위에 올라설수 있다.",
                "오랫동안 꿈을 그리는 사람은 마침내 그 꿈을 닮아 간다.",
                "좋은 성과를 얻으려면 한 걸음 한 걸음이 힘차고 충실하지 않으면 안 된다.",
                "행복은 습관이다,그것을 몸에 지니라.",
                "성공의 비결은 단 한 가지, 잘할 수 있는 일에 광적으로 집중하는 것이다.",
                "자신감 있는 표정을 지으면 자신감이 생긴다.",
                "평생 살 것처럼 꿈을 꾸어라.그리고 내일 죽을 것처럼 오늘을 살아라.",
                "네 믿음은 네 생각이 된다 . 네 생각은 네 말이 된다. 네말은 네 행동이 된다 네행동은 네 습관이된다 . 네 습관은 네 가치가 된다 . 네 가치는 네 운명이 된다."
         };
        int randInt = (int)(Math.random()*quotes.length);
        model.addAttribute("quotes", quotes[randInt]);
        return "quote";
    }
}


package com.example.firstproject.controller;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import com.example.firstproject.repository.ArticleRepository;

@Controller
public class ArticleController {
    //의존성 주입 = DI, Dependency Injection
    @Autowired // 스프링 부트가 만들어 놓은 레포지토리 객체 주입(DI)
    private ArticleRepository articleRepository; // 객체 선언

    @GetMapping("/articles/new")
    public String newArticleForm(){
        return "articles/new";
    }

    @PostMapping("/articles/create")
    public String createArticle(ArticleForm form){ //form 태그 데이터륽 DTO로 받는 순간
        System.out.println(form.toString()); //DTO에 폼 데이터가 잘 담겼는지 확인하는

        //1. dto -> Entity 변환

        Article article = form.toEntity();
        System.out.println(article.toString());

        //2. 레포 -> Entity -> DB 저장

        Article saved = articleRepository.save(article); //DB에 저장하는 코드 1줄
        System.out.println(saved.toString());


        return "";
    }
}

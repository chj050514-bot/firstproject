package com.example.firstproject.controller;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import com.example.firstproject.repository.ArticleRepository;

import java.util.List;


@Slf4j //Simple Logging Facade for Java
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
        // System.out.println(form.toString()); DTO에 폼 데이터가 잘 담겼는지 확인하는

        log.info(form.toString());
        log.debug(form.toString());
        //1. dto -> Entity 변환

        Article article = form.toEntity();
        log.trace(article.toString());

        //2. 레포 -> Entity -> DB 저장

        Article saved = articleRepository.save(article); //DB에 저장하는 코드 1줄
        log.info(saved.toString());


        // 사용자가 작성한 게시글 상세페이지
        //
        return "redirect:/articles/"+ saved.getId();
    }

    @GetMapping ("/articles/{id}")
    public String show(@PathVariable Long id, Model model){

        log.info("id ="+id);

        Article articleEntity = articleRepository.findById(id).orElse(null);

        model.addAttribute("article", articleEntity); //attribute

        return "articles/show";

    }
    @GetMapping("/articles")
    public String index(Model model){

        //모든 데이터 가져오기
        Iterable<Article> articleEntityList = articleRepository.findAll();

        // 모델에 등록
        model.addAttribute("articlesList", articleEntityList);

        // 화면 전달
        return "articles/index";
    }

    }


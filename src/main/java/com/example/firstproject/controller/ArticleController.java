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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;


@Slf4j //Simple Logging Facade for Java
@Controller
public class ArticleController {
    //의존성 주입 = DI, Dependency Injection
    @Autowired // 스프링 부트가 만들어 놓은 레포지토리 객체 주입(DI)
    private ArticleRepository articleRepository; // 객체 선언

    @GetMapping("/articles/new")
    public String newArticleForm() {
        return "articles/new";
    }

    @PostMapping("/articles/create")
    public String createArticle(ArticleForm form) { //form 태그 데이터륽 DTO로 받는 순간
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
        return "redirect:/articles/" + saved.getId();
    }

    @GetMapping("/articles/{id}")
    public String show(@PathVariable Long id, Model model) {

        log.info("id =" + id);

        Article articleEntity = articleRepository.findById(id).orElse(null);

        model.addAttribute("article", articleEntity); //attribute

        return "articles/show";

    }

    @GetMapping("/articles")
    public String index(Model model) {

        //모든 데이터 가져오기
        Iterable<Article> articleEntityList = articleRepository.findAll();

        // 모델에 등록
        model.addAttribute("articlesList", articleEntityList);

        // 화면 전달
        return "articles/index";
    }

    @GetMapping ("/articles/{id}/edit")
    public String edit(@PathVariable Long id, Model model){

        Article articleEntity = articleRepository.findById(id).orElse(null);

        model.addAttribute("article", articleEntity); //attribute

        return "articles/edit";

    }

    @PostMapping ("/articles/update")
    public String update(ArticleForm form){
        log.info(form.toString());
        //1. DTO를 Entity로 변환
        Article entity = form.toEntity();
        log.info(entity.toString());

        //2. 엔티티를 DB 저장
        //2-1. 기존 데이터를 수정 하려고 함-> 기존 데이터 가져오기
        Article target = articleRepository.findById(entity.getId())
                .orElse(null);
        //2-2. 기존데이터를 갱신
        if(target != null){
            articleRepository.save(entity);
        }

        //3. 수정 결과 리다이렉트
        return "redirect:/articles/" + target.getId();
    }

    @GetMapping("/articles/{id}/delete")     //RedirectAttribute -> 삭제하고 다음 화면으로 넘어가는
    public String delete(@PathVariable Long id, RedirectAttributes rttr) {

        log.info(   "삭제 요청 수신 !");

        Article target = articleRepository.findById(id).orElse(null);
        log.info(target.toString());

        if(target != null){
            articleRepository.delete(target);
            rttr.addFlashAttribute("msg","삭제가 완료되었습니다."); // 상단에 삭제가 완료되었다는 걸 띄우는 .. 얘는 계솏 저장하진 않고 한 번 띄우고 만다.
        }

        return "redirect:/articles";
    }
}


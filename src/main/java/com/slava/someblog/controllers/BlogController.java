package com.slava.someblog.controllers;

import com.slava.someblog.models.Articles;
import com.slava.someblog.repo.ArticlesRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@Controller
@AllArgsConstructor

public class BlogController {

    private final ArticlesRepository articlesRepository;

    @GetMapping("/blog")
    public String blog(Model model) {
        Iterable<Articles> articles = articlesRepository.findAll();
        model.addAttribute("posts", articles);
        return "blog";
    }

    @GetMapping("/blog/add")
    public String blogAdd(Model model) {
        return "blog-add";
    }

    @PostMapping("/blog/add")
    public String blogPostAdd(@RequestParam String title,
                              @RequestParam String announce,
                              @RequestParam String fullText,
                              Model model) {
        Articles article = new Articles(title, announce, fullText);
        articlesRepository.save(article);
        return "redirect:/blog";
    }

    @GetMapping("/blog/{id}")
    public String blogDetails(@PathVariable(value = "id") long id, Model model) {
        if (!articlesRepository.existsById(id)) {
            return "redirect:/blog";
        }
        Optional<Articles> article = articlesRepository.findById(id);
        ArrayList<Articles> result = new ArrayList<>();
        article.ifPresent(result::add);
        model.addAttribute("post", result);
        return "blog-details";
    }

    @GetMapping("/blog/{id}/edit")
    public String blogEdit(@PathVariable(value = "id") long id, Model model) {
        if (!articlesRepository.existsById(id)) {
            return "redirect:/blog";
        }
        Optional<Articles> article = articlesRepository.findById(id);
        ArrayList<Articles> result = new ArrayList<>();
        article.ifPresent(result::add);
        model.addAttribute("post", result);
        return "blog-edit";
    }

    @PostMapping("/blog/{id}/edit")
    public String blogPostUpdate(@PathVariable(value = "id") long id,
                              @RequestParam String title,
                              @RequestParam String announce,
                              @RequestParam String fullText,
                              Model model) {
        Articles article = articlesRepository.findById(id).orElseThrow();
        article.setTitle(title);
        article.setAnnounce(announce);
        article.setFullText(fullText);
        articlesRepository.save(article);
        return "redirect:/blog";
    }

    @PostMapping("/blog/{id}/remove")
    public String blogPostDelete(@PathVariable(value = "id") long id, Model model) {
        Articles article = articlesRepository.findById(id).orElseThrow();
        articlesRepository.delete(article);
        return "redirect:/blog";
    }
}

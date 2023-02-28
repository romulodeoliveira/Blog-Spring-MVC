package com.spring.blog.controllers;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.blog.models.Post;
import com.spring.blog.services.PostService;

import jakarta.validation.Valid;

@Controller
public class PostController {

    @Autowired
    PostService postService;

    // GET

    @RequestMapping(value = "/posts", method = RequestMethod.GET)
    public ModelAndView getPosts() {
        ModelAndView mv = new ModelAndView("posts");
        List<Post> posts = postService.findAll();
        mv.addObject("posts", posts);
        return mv;
    }

    // GET One

    @RequestMapping(value = "/posts/{id}", method = RequestMethod.GET)
    public ModelAndView getPostDetails(@PathVariable("id") long id) {
        ModelAndView mv = new ModelAndView("postDetails");
        Post post = postService.findById(id);
        mv.addObject("post", post);
        return mv;
    }

    // POST

    @RequestMapping(value = "/newpost", method = RequestMethod.GET)
    public String getPostForm() {
        return "postForm";
    }

    @RequestMapping(value = "/newpost", method = RequestMethod.POST)
    public String savePost(@Valid Post post, BindingResult result, RedirectAttributes attributes) {
        if (result.hasErrors()) {
            attributes.addFlashAttribute("message", "Verifique se preencheu todos os campos obrigatórios!");
            return "redirect:/newpost";
        }
        post.setDate(LocalDate.now());
        postService.save(post);
        return "redirect:/posts";
    }

    // PUT

    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public ModelAndView getEditForm(@PathVariable("id") long id) {
        ModelAndView mv = new ModelAndView("editForm");
        Post post = postService.findById(id);
        mv.addObject("post", post);
        return mv;
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public String updatePost(@PathVariable("id") long id, @Valid Post post, BindingResult result,
            RedirectAttributes attributes) {
        if (result.hasErrors()) {
            attributes.addFlashAttribute("message", "Verifique se preencheu todos os campos obrigatórios!");
            return "redirect:/posts/" + id + "/edit";
        }
        post.setId(id);
        post.setDate(LocalDate.now());
        postService.save(post);
        return "redirect:/posts/" + id;
    }

    // DELETE

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public ModelAndView getDeletePost(@PathVariable("id") long id) {
        ModelAndView mv = new ModelAndView("deletePost");
        Post post = postService.findById(id);
        mv.addObject("post", post);
        return mv;
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public String deletePost(@PathVariable("id") long id) {
        postService.deleteById(id);
        return "redirect:/posts";
    }

}

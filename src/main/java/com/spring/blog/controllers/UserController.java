package com.spring.blog.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.blog.models.User;
import com.spring.blog.services.MyUserDetailsService;

import jakarta.validation.Valid;

@Controller
public class UserController {

    @Autowired
    MyUserDetailsService userService;

    // GET

    @RequestMapping(value = "/all-users", method = RequestMethod.GET)
    public ModelAndView getUsers() {
        ModelAndView mv = new ModelAndView("users");
        List<User> users = userService.findAll();
        mv.addObject("users", users);
        return mv;
    }

    // POST

    @RequestMapping(value = "/new-user", method = RequestMethod.GET)
    public String getUserForm() {
        return "userForm";
    }

    @RequestMapping(value = "/new-user", method = RequestMethod.POST)
    public String saveUser(@Valid User user, BindingResult result, RedirectAttributes attributes) {

        if (result.hasErrors()) {
            attributes.addFlashAttribute("message", "Verifique se preencheu todos os campos obrigatórios!");
            return "redirect:/new-user";
        }

        userService.save(user);
        return "redirect:/all-users";
    }

}

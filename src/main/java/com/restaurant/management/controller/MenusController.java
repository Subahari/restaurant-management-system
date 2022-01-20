package com.restaurant.management.controller;

import com.restaurant.management.Logindomain.Amenu;
import com.restaurant.management.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class MenusController {
    @Autowired
    private StudentService service;

    @GetMapping("/menuadd")
    public String viewHomePage(Model model) {
        List<Amenu> liststudent = service.listAll();
        model.addAttribute("liststudent", liststudent);

        return "menuadd";
    }

    @GetMapping("/new")
    public String add(Model model) {
        model.addAttribute("student", new Amenu());
        return "new";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveStudent(@ModelAttribute("student") Amenu std) {
        service.save(std);
        return "redirect:/menuadd";
    }

    @RequestMapping("/edit/{id}")
    public ModelAndView showEditStudentPage(@PathVariable(name = "id") int id) {
        ModelAndView mav = new ModelAndView("new");
        Amenu std = service.get(id);
        mav.addObject("student", std);
        return mav;

    }
    @RequestMapping("/delete/{id}")
    public String deletestudent(@PathVariable(name = "id") int id) {
        service.delete(id);
        return "redirect:/";
    }
}

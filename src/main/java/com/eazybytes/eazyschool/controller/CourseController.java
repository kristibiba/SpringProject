package com.eazybytes.eazyschool.controller;

import com.eazybytes.eazyschool.model.Courses;
import com.eazybytes.eazyschool.repository.CoursesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class CourseController {

    @Autowired
    private CoursesRepository courseService;

    @GetMapping("/courses")
    public ModelAndView getCourses(Model model) {
        List<Courses> courses = courseService.findAll();

        ModelAndView modelAndView = new ModelAndView("courses");
        modelAndView.addObject("courses", courses);

        return modelAndView;
    }


}
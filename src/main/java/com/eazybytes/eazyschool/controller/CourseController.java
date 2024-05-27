package com.eazybytes.eazyschool.controller;

import com.eazybytes.eazyschool.model.CourseRequest;
import com.eazybytes.eazyschool.model.Courses;
import com.eazybytes.eazyschool.model.Person;
import com.eazybytes.eazyschool.repository.CourseRequestRepository;
import com.eazybytes.eazyschool.repository.CoursesRepository;
import com.eazybytes.eazyschool.service.CourseRequestService;
import com.eazybytes.eazyschool.service.PersonService;

import jakarta.servlet.http.HttpSession;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CourseController {

    @Autowired
    private CoursesRepository courseService;

    @Autowired
    private PersonService personService;

    @Autowired
    private CourseRequestService courseRequestService;

    @GetMapping("/courses")
    public ModelAndView getCourses(Model model) {
        List<Courses> courses = courseService.findAll();

        ModelAndView modelAndView = new ModelAndView("courses");
        modelAndView.addObject("courses", courses);

        return modelAndView;
    }

    @GetMapping("/courses_request/{courseId}")
    public ModelAndView getCourseDetails(@PathVariable("courseId") int courseId, HttpSession session, Model model) {
        Courses course = courseService.findById(courseId).orElse(null);
        ModelAndView modelAndView = new ModelAndView("courses_request");
        modelAndView.addObject("course", course);

        Person person = (Person) session.getAttribute("loggedInPerson");
        if (person == null) {
            // Handle the case where the person is not logged in
            return new ModelAndView("redirect:/login"); // Redirect to login page or appropriate action
        }

        boolean isEnrolled = courseService.isPersonEnrolledInCourse(person.getPersonId(), courseId);
        modelAndView.addObject("isEnrolled", isEnrolled);

        return modelAndView;
    }

    @PostMapping("/courses_request/request")
    public String sendCourseRequest(@RequestParam("courseId") int courseId,
                                    @RequestParam("requestType") String requestType, HttpSession session) {
        Person person = (Person) session.getAttribute("loggedInPerson");
        if (person == null) {
            // Handle the case where the person is not logged in
            return "redirect:/login"; // Redirect to login page or appropriate action
        }

        courseRequestService.sendRequest(person.getPersonId(), courseId, requestType);
        return "redirect:/courses_request/" + courseId;
    }

    @GetMapping("/courses_request/{courseId}/leave")
    public String requestLeaveCourse(@PathVariable("courseId") int courseId, HttpSession session) {
        Person person = (Person) session.getAttribute("loggedInPerson");
        courseRequestService.sendRequest(person.getPersonId(), courseId, "leave");
        return "redirect:/courses_request/" + courseId;
    }
}
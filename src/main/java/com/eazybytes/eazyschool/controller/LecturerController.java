package com.eazybytes.eazyschool.controller;

import com.eazybytes.eazyschool.model.CourseDocuments;
import com.eazybytes.eazyschool.model.Courses;
import com.eazybytes.eazyschool.model.Person;
import com.eazybytes.eazyschool.repository.CourseDocumentsRepository;
import com.eazybytes.eazyschool.repository.CoursesRepository;
import com.eazybytes.eazyschool.repository.PersonRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.util.StringUtils;

import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.HashSet;

@Slf4j
@Controller
@RequestMapping("lecturer")
public class LecturerController {

    @Autowired
    CoursesRepository coursesRepository;

    @Autowired
    CourseDocumentsRepository courseDocumentsRepository;

    @Autowired
    PersonRepository personRepository;

    private static final String UPLOAD_DIR = "C:\\Users\\User\\Desktop\\javaenterprice\\JavaEnterpriseApplicationsCourseWork\\src\\main\\resources\\static\\assets\\uploads\\";

    @GetMapping("/courses")
    public ModelAndView displayCourses(HttpSession session) {
        Person person = (Person) session.getAttribute("loggedInPerson");
        List<Courses> courses = null;
        try {
            courses = coursesRepository.findByPersonId(person.getPersonId());
        } catch (Exception e) {
            e.printStackTrace();
            ModelAndView modelAndView = new ModelAndView("error.html");
            modelAndView.addObject("error", "An error occurred while fetching courses: " + e.getMessage());
            return modelAndView;
        }

        for (Courses course : courses) {
            // List<CourseDocuments> documents = null;

            List<CourseDocuments> documents = courseDocumentsRepository.findByCoursesCourseId(course.getCourseId());
            try {
                documents = courseDocumentsRepository.findByCoursesCourseId(course.getCourseId());
                Set<CourseDocuments> documentSet = new HashSet<>(documents);
                course.setDocuments(documentSet);
            } catch (Exception e) {
                e.printStackTrace();
                ModelAndView modelAndView = new ModelAndView("error.html");
                modelAndView.addObject("error", "An error occurred while fetching documents for course " + course.getCourseId() + ": " + e.getMessage());
                return modelAndView;
            }
        }

        ModelAndView modelAndView = new ModelAndView("lecturer_courses.html");
        modelAndView.addObject("courses", courses);
        return modelAndView;
    }

    // @PostMapping("/uploadMaterial")
    // public String uploadMaterial(@RequestParam("courseId") int courseId, 
    //                             @RequestParam("file") MultipartFile file,
    //                             HttpSession session, Model model) {
    //     Person person = (Person) session.getAttribute("loggedInPerson");
    //     Optional<Courses> optionalCourse = coursesRepository.findById(courseId);
    //     if (optionalCourse.isPresent()) {
    //         Courses course = optionalCourse.get();
    //         if (!file.isEmpty() && file.getContentType().equals("application/pdf") && file.getSize() <= 10485760) {
    //             try {
    //                 String filename = StringUtils.cleanPath(file.getOriginalFilename());
    //                 Path path = Paths.get(UPLOAD_DIR + filename);
    //                 Files.copy(file.getInputStream(), path);

    //                 CourseDocuments document = new CourseDocuments();
    //                 document.setFileName(filename);
    //                 document.setFilePath("/assets/uploads/" + filename);
    //                 document.setCourses(course);
    //                 courseDocumentsRepository.save(document);

    //                 return "redirect:/lecturer/courses";
    //             } catch (IOException e) {
    //                 e.printStackTrace();
    //                 model.addAttribute("error", "File upload failed: " + e.getMessage());
    //             }
    //         } else {
    //             model.addAttribute("error", "Invalid file or file format not supported");
    //         }
    //     } else {
    //         model.addAttribute("error", "Course not found");
    //     }
    //     return "lecturer_courses.html";
    // }
    
    
    @PostMapping("/uploadMaterial")
    public String uploadMaterial(@RequestParam("courseId") int courseId, 
                                @RequestParam("file") MultipartFile file,
                                HttpSession session, Model model) {
        Optional<Courses> optionalCourse = coursesRepository.findById(courseId);
        if (optionalCourse.isPresent()) {
            Courses course = optionalCourse.get();
            if (!file.isEmpty()) {
                if ("application/pdf".equals(file.getContentType())) {
                    if (file.getSize() <= 10485760) { // 10 MB = 10 * 1024 * 1024 bytes
                        try {
                            String filename = StringUtils.cleanPath(file.getOriginalFilename());
                            Path path = Paths.get(UPLOAD_DIR + filename);
                            Files.copy(file.getInputStream(), path);
    
                            CourseDocuments document = new CourseDocuments();
                            document.setFileName(filename);
                            document.setFilePath("/assets/uploads/" + filename);
                            document.setCourses(course);
                            document.setUploadedAt(LocalDateTime.now());
                            courseDocumentsRepository.save(document);
    
                            return "redirect:/lecturer/courses";
                        } catch (IOException e) {
                            model.addAttribute("error", "File upload failed: " + e.getMessage());
                            return "lecturer_courses";
                        }
                    } else {
                        model.addAttribute("error", "File size exceeds the maximum allowed size of 10 MB.");
                    }
                } else {
                    model.addAttribute("error", "Only PDF files are allowed.");
                }
            } else {
                model.addAttribute("error", "No file selected for upload.");
            }
        } else {
            model.addAttribute("error", "Course not found.");
        }
        return "lecturer_courses";
    }
    
      
    
    
    @PostMapping("/deleteMaterial")
    public String deleteMaterial(@RequestParam("documentId") int documentId) {
        courseDocumentsRepository.deleteById(documentId);
        return "redirect:/lecturer/courses";
    }

    @GetMapping("/students")
    public ModelAndView viewStudents(@RequestParam("courseId") int courseId, Model model) {
        Optional<Courses> optionalCourse = coursesRepository.findById(courseId);
        ModelAndView modelAndView = new ModelAndView("lecturer_students.html");
        if (optionalCourse.isPresent()) {
            Courses course = optionalCourse.get();
            modelAndView.addObject("students", course.getPersons());
        }
        return modelAndView;
    }




}

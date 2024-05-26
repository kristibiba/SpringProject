package com.eazybytes.eazyschool.service;


import com.eazybytes.eazyschool.model.Courses;
import com.eazybytes.eazyschool.model.Person;
import com.eazybytes.eazyschool.repository.CoursesRepository;
import com.eazybytes.eazyschool.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminService {

    @Autowired
    PersonRepository personRepository;

    @Autowired
    CoursesRepository coursesRepository;

    public List<Person> getLecturers() {
        return personRepository.findByRolesRoleName("LECTURER");
    }

    public List<Courses> getCourses() {
        return coursesRepository.findAll();
    }

    public void assignCourseToLecturer(int lecturerId, int courseId) {
        Optional<Person> lecturerOpt = personRepository.findById(lecturerId);
        Optional<Courses> courseOpt = coursesRepository.findById(courseId);

        if (lecturerOpt.isPresent() && courseOpt.isPresent()) {
            Person lecturer = lecturerOpt.get();
            Courses course = courseOpt.get();
            lecturer.getCourses().add(course);
            course.getPersons().add(lecturer);
            personRepository.save(lecturer);
            coursesRepository.save(course);
        }
    }
}

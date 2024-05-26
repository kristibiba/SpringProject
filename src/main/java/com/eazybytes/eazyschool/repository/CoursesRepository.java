package com.eazybytes.eazyschool.repository;

import com.eazybytes.eazyschool.model.Courses;
import com.eazybytes.eazyschool.model.Person;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RepositoryRestResource(path = "courses")
public interface CoursesRepository extends JpaRepository<Courses, Integer> {

    List<Courses> findByOrderByNameDesc();

    List<Courses> findByOrderByName();

    @Query("SELECT c FROM Courses c JOIN c.persons p WHERE p.personId = :personId")
    List<Courses> findByPersonId(@Param("personId") int personId);

    // Add the following method
    @Query("SELECT c FROM Courses c WHERE c.courseId = :courseId")
    Courses findById(@Param("courseId") Long courseId);

    
    List<Courses> findByLecturerPersonId(int lecturerId);
    
    Courses findByCourseId(int courseId);


    Person readByEmail(String email);

    List<Person> findByRoles_RoleName(String roleName);

}

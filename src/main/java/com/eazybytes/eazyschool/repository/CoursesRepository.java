package com.eazybytes.eazyschool.repository;

import com.eazybytes.eazyschool.model.Courses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
//@RepositoryRestResource(exported = false)
@RepositoryRestResource(path = "courses")
public interface CoursesRepository extends JpaRepository<Courses, Integer> {

    /*
    Spring Data JPA allows us to apply static sorting by adding the OrderBy keyword
    to the method name along with the property name and sort direction (Asc or Desc).
    * */
    List<Courses> findByOrderByNameDesc();

    /*
    The Asc keyword is optional as OrderBy, by default,
    sorts the results in the ascending order.
    * */
    List<Courses> findByOrderByName();

    Optional<Courses> findById(Integer id);


    @Query("SELECT c FROM Courses c JOIN c.persons p WHERE p.personId = :personId")
    List<Courses> findByPersonId(@Param("personId") int personId);

    @Query("SELECT CASE WHEN COUNT(pc) > 0 THEN true ELSE false END " +
    "FROM PersonCourse pc " +
    "WHERE pc.id.personId = :personId AND pc.id.courseId = :courseId")
boolean isPersonEnrolledInCourse(@Param("personId") int personId, @Param("courseId") int courseId);

}

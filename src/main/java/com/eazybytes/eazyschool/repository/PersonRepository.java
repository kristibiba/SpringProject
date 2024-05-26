package com.eazybytes.eazyschool.repository;

import com.eazybytes.eazyschool.model.Courses;
import com.eazybytes.eazyschool.model.Person;
import com.eazybytes.eazyschool.model.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {

    Person readByEmail(String email);

    List<Person> findByRolesRoleName(String roleName);

    @Query("SELECT r FROM Roles r WHERE r.roleName = :roleName")
    Roles findRoleByName(@Param("roleName") String roleName);

    // Add the following method
    @Query("SELECT p FROM Person p WHERE p.personId = :personId")
    Person findByPersonId(@Param("personId") int personId);
}

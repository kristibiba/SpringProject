package com.eazybytes.eazyschool.repository;

import com.eazybytes.eazyschool.model.Person;
import com.eazybytes.eazyschool.model.Roles;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {

    Person readByEmail(String email);

    List<Person> findByRolesRoleName(String roleName);

    @Query("SELECT r FROM Roles r WHERE r.roleName = :roleName")
    Roles findRoleByName(@Param("roleName") String roleName);

}

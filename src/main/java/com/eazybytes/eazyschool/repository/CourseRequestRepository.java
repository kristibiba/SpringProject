package com.eazybytes.eazyschool.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eazybytes.eazyschool.model.CourseRequest;

@Repository
public interface CourseRequestRepository extends JpaRepository<CourseRequest, Integer> {
    // Add custom query methods if needed
}
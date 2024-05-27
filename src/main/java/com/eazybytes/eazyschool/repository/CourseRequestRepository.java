package com.eazybytes.eazyschool.repository;

import com.eazybytes.eazyschool.model.CourseRequest;
import com.eazybytes.eazyschool.model.RequestType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRequestRepository extends JpaRepository<CourseRequest, Integer> {
    // List<CourseRequest> findByStatus(String status);
    // List<CourseRequest> findByRequestType(RequestType requestType);
}

package com.eazybytes.eazyschool.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eazybytes.eazyschool.model.CourseRequest;
import com.eazybytes.eazyschool.model.RequestType;
import com.eazybytes.eazyschool.repository.CourseRequestRepository;

@Service
public class CourseRequestService {

    @Autowired
    private CourseRequestRepository courseRequestRepository;

    public void sendRequest(int personId, int courseId, String requestType) {
        RequestType type = RequestType.valueOf(requestType.toUpperCase());
        CourseRequest courseRequest = new CourseRequest(personId, courseId, type);
        courseRequestRepository.save(courseRequest);
    }

    public void saveRequest(CourseRequest request) {
        courseRequestRepository.save(request);
    }
 
    public List<CourseRequest> getAllRequests() {
        return courseRequestRepository.findAll();
    }
    
}

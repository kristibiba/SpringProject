package com.eazybytes.eazyschool.model;

import com.eazybytes.eazyschool.constants.RequestType;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class CourseRequest extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    private int studentId;
    
    private int courseId;
    
    @Enumerated(EnumType.STRING)
    private RequestType requestType;
    
}
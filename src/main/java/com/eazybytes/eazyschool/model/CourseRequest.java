package com.eazybytes.eazyschool.model;

import jakarta.persistence.*;
import lombok.Setter;
import lombok.Getter;
import java.time.LocalDateTime;


@Entity
@Getter
@Setter
@Table(name = "course_requests")
public class CourseRequest extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "request_id")
    private int id;

    @Column(name = "person_id")
    private int personId;

    @Column(name = "course_id")
    private int courseId;

    @Enumerated(EnumType.STRING)
    @Column(name = "request_type")
    private RequestType requestType;


    public CourseRequest() {}

    public CourseRequest(int personId, int courseId, RequestType requestType) {
        this.personId = personId;
        this.courseId = courseId;
        this.requestType = requestType;
    }
}

package com.eazybytes.eazyschool.model;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "person_courses")
public class PersonCourse {

    @EmbeddedId
    private PersonCourseId id;

    // Constructors, getters, and setters

    public PersonCourse() {}

    public PersonCourse(PersonCourseId id) {
        this.id = id;
    }

    public PersonCourseId getId() {
        return id;
    }

    public void setId(PersonCourseId id) {
        this.id = id;
    }
}

package com.eazybytes.eazyschool.model;

import java.io.Serializable;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class PersonCourseId implements Serializable {

    @Column(name = "person_id")
    private int personId;

    @Column(name = "course_id")
    private int courseId;

    // Constructors, getters, setters, equals and hashCode methods

    public PersonCourseId() {}

    public PersonCourseId(int personId, int courseId) {
        this.personId = personId;
        this.courseId = courseId;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonCourseId that = (PersonCourseId) o;
        return personId == that.personId && courseId == that.courseId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(personId, courseId);
    }
}

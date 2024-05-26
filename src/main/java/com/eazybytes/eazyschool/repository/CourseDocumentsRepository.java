package com.eazybytes.eazyschool.repository;

import com.eazybytes.eazyschool.model.CourseDocuments;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CourseDocumentsRepository extends JpaRepository<CourseDocuments, Integer> {
    List<CourseDocuments> findByCoursesCourseId(int courseId);
}

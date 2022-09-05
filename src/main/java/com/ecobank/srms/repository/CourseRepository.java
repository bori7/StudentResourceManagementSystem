package com.ecobank.srms.repository;

import com.ecobank.srms.model.Courses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository< Courses, Long> {

    Courses findByCourseId(Long courseId);

    Courses findAllByNameOfCourse (String nameOfCourse);
}

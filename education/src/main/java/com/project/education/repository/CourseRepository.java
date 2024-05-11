package com.project.education.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.education.entity.Course;

public interface CourseRepository extends JpaRepository<Course, Long> {

}

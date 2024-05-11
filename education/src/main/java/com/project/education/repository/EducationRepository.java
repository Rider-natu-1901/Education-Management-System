package com.project.education.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.education.entity.Education;

public interface EducationRepository extends JpaRepository<Education, Long> {

}

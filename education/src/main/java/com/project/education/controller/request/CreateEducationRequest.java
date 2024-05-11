package com.project.education.controller.request;

import com.project.education.dto.CourseDTO;
import com.project.education.dto.EducationDTO;

import lombok.Data;

@Data
public class CreateEducationRequest {
	private EducationDTO educationDTO;
	private CourseDTO courseDTO;

}

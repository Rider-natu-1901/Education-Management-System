package com.project.education.controller.response;

import com.project.education.dto.CourseDTO;

import lombok.Data;

@Data
public class ResponseEducationDetails {
	private double percentageScored;
	private int startYear;
	private int endYear;
	private String location;
	private String type;
	private String institutionName;
	private String degree;
	private int collegeRank;
	private String studentName;
	private CourseDTO courseDTO;

}

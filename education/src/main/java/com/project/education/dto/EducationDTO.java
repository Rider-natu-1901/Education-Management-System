package com.project.education.dto;

import lombok.Data;

@Data
public class EducationDTO {
	private Long id;
	private double percentageScored;
	private int startYear;
	private int endYear;
	private String location;
	private String type;
	private String institutionName;
	private String degree;
	private int collegeRank;
	private String emailId;
	private String studentName;

}

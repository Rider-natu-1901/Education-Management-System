package com.project.education.controller.response;

import lombok.Data;

@Data
public class UpdateEducationResponse {
	private double percentageScored;
	private int startYear;
	private int endYear;
	private String location;
	private String type;
	private String institutionName;
}

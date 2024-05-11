package com.project.education.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class CourseDTO {

	private List<CourseDetails> coursedetails = new ArrayList<>();

	@Data
	public static class CourseDetails {
		private Long id;
		private String name;
		private String description;
		private int duration;
	}
}

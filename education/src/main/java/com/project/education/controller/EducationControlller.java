package com.project.education.controller;

import java.time.Duration;
import java.time.LocalTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.education.config.StatusConstant;
import com.project.education.controller.request.CreateEducationRequest;
import com.project.education.controller.response.ResponseEducationID;
import com.project.education.controller.response.ResponseMessage;
import com.project.education.controller.response.UpdateCourseResponse;
import com.project.education.controller.response.UpdateEducationResponse;
import com.project.education.service.EducationService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping("/api/education/e")
public class EducationControlller {

	private final EducationService educationService;

	/**
	 * Endpoint to save education details along with courses.
	 *
	 * @param createEducationRequest The request containing education and course
	 *                               details.
	 * @return ResponseEntity containing the ID of the saved education.
	 */
	@PostMapping("/educations/save")
	public ResponseEntity<ResponseEducationID> saveEducation(
			@RequestBody CreateEducationRequest createEducationRequest) {
		LocalTime start = LocalTime.now();
		log.debug("Received request to save education details: {}", createEducationRequest);
		Long response = educationService.saveEducationDetails(createEducationRequest);
		log.debug("Saved education with ID: {}", response);
		long milliseconds = Duration.between(start, LocalTime.now()).toMillis();
		log.info("/educations/save - Time taken: " + milliseconds);
		return new ResponseEntity<>(new ResponseEducationID(response), HttpStatus.OK);
	}

	/**
	 * Endpoint to retrieve education details by ID along with associated courses.
	 *
	 * @param educationId The ID of the education to retrieve.
	 * @return ResponseEntity containing the education details and associated
	 *         courses.
	 */
	@GetMapping("/educations/{educationId}/get-education-details")
	public ResponseEntity<CreateEducationRequest> getEducationDetailsByEducationId(@PathVariable Long educationId) {
		LocalTime start = LocalTime.now();
		log.debug("Fetching education details for ID: {}", educationId);
		CreateEducationRequest educationRequest = educationService.getEducationDetailsByEducationId(educationId);
		log.debug("Retrieved education details: {}", educationRequest);
		long milliseconds = Duration.between(start, LocalTime.now()).toMillis();
		log.info("/educations/{educationId}/get-education-details - Time taken: " + milliseconds + " milliseconds");
		return ResponseEntity.ok(educationRequest);
	}

	/**
	 * Endpoint to update education details by ID along with associated courses.
	 *
	 * @param educationId             The ID of the education to update.
	 * @param updateEducationResponse The updated education details.
	 * @return ResponseEntity containing a success message.
	 */
	@PutMapping("/educations/{educationId}/update-education")
	public ResponseEntity<ResponseMessage> updateEducationByEducationId(@PathVariable Long educationId,
			@RequestBody UpdateEducationResponse updateEducationResponse) {
		LocalTime start = LocalTime.now();

		log.debug("Received request to update education with ID {}: {}", educationId, updateEducationResponse);
		educationService.updateEducationByEducationId(educationId, updateEducationResponse);
		log.info("Successfully updated education with ID: {}", educationId);
		long milliseconds = Duration.between(start, LocalTime.now()).toMillis();
		log.info("/educations/{educationId}/update-education - Time taken: " + milliseconds + " milliseconds");
		return ResponseEntity.ok(new ResponseMessage(StatusConstant.SUCCESS));
	}

	/**
	 * Endpoint to update course details by course ID.
	 *
	 * @param courseId             The ID of the course to update.
	 * @param updateCourseResponse The updated course details.
	 * @return ResponseEntity containing a success message.
	 */
	@PutMapping("/course/{courseId}/update-course")
	public ResponseEntity<ResponseMessage> updateCourseByCourseId(@PathVariable Long courseId,
			@RequestBody UpdateCourseResponse updateCourseResponse) {
		LocalTime start = LocalTime.now();
		log.debug("Received request to update course with ID {}: {}", courseId, updateCourseResponse);
		educationService.updateCourseByCourseId(courseId, updateCourseResponse);
		log.info("Successfully updated course with ID: {}", courseId);
		long milliseconds = Duration.between(start, LocalTime.now()).toMillis();
		log.info("/course/{courseId}/update-course - Time taken: " + milliseconds + " milliseconds");
		return ResponseEntity.ok(new ResponseMessage(StatusConstant.SUCCESS));
	}
}

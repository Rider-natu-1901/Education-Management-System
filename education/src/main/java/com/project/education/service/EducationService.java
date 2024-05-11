package com.project.education.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.project.education.controller.request.CreateEducationRequest;
import com.project.education.controller.response.UpdateCourseResponse;
import com.project.education.controller.response.UpdateEducationResponse;
import com.project.education.dto.CourseDTO;
import com.project.education.dto.EducationDTO;
import com.project.education.entity.Course;
import com.project.education.entity.Education;
import com.project.education.repository.CourseRepository;
import com.project.education.repository.EducationRepository;
import com.project.education.util.EducationUtil;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class EducationService {

	private final EducationRepository educationRepository;

	private final CourseRepository courseRepository;

	/**
	 * Saves education details along with associated courses.
	 *
	 * @param createEducationRequest The request containing education details and
	 *                               associated courses.
	 * @return The ID of the saved education.
	 */
	public Long saveEducationDetails(CreateEducationRequest createEducationRequest) {
		log.debug("Received request to save education details: {}", createEducationRequest);

		EducationDTO educationDTO = createEducationRequest.getEducationDTO();
		CourseDTO courseDTO = createEducationRequest.getCourseDTO();
		Education education = EducationUtil.map(educationDTO, Education.class);
		education.setCreatedTime(LocalDateTime.now());
		List<Course> courseList = courseDTO.getCoursedetails().stream().map(coursedetails -> {

			Course course = EducationUtil.map(coursedetails, Course.class);
			course.setEducation(education);
			return course;

		}).collect(Collectors.toList());

		log.info("Saving education: {}", education);
		education.setCourses(courseList);
		educationRepository.save(education);

		log.info("Saved education with ID: {}", education.getId());
		return education.getId();

	}

	/**
	 * Retrieves education details by education ID along with associated courses.
	 *
	 * @param educationId The ID of the education to retrieve.
	 * @return CreateEducationRequest containing education details and associated
	 *         courses.
	 */

	public CreateEducationRequest getEducationDetailsByEducationId(Long educationId) {
		log.debug("Fetching education details for ID: {}", educationId);

		CreateEducationRequest createEducationRequest = new CreateEducationRequest();

		Optional<Education> optEducation = educationRepository.findById(educationId);
		if (optEducation.isPresent()) {
			Education education = optEducation.get();
			EducationDTO educationDTO = EducationUtil.map(education, EducationDTO.class);

			List<CourseDTO.CourseDetails> courseDetailsList = education.getCourses().stream()
					.map(course -> EducationUtil.map(course, CourseDTO.CourseDetails.class))
					.collect(Collectors.toList());

			CourseDTO courseDTO = new CourseDTO();
			courseDTO.setCoursedetails(courseDetailsList);

			createEducationRequest.setEducationDTO(educationDTO);
			createEducationRequest.setCourseDTO(courseDTO);

		}
		log.debug("Retrieved education details: {}", createEducationRequest);
		return createEducationRequest;
	}

	/**
	 * Updates education details by education ID along with associated courses.
	 *
	 * @param educationId             The ID of the education to update.
	 * @param updateEducationResponse The updated education details.
	 */
	public void updateEducationByEducationId(Long educationId, UpdateEducationResponse updateEducationResponse) {
		log.debug("Received request to update education with ID {}: {}", educationId, updateEducationResponse);
		Optional<Education> optEducation = educationRepository.findById(educationId);
		if (optEducation.isPresent()) {
			Education education = optEducation.get();
			EducationUtil.map(updateEducationResponse, education);
			log.info("Updating education: {}", education);
			educationRepository.save(education);
		} else {
			// will use custome exception
			log.info("Education with ID {} does not exist", educationId);
			throw new RuntimeException("Education with educationId " + educationId + " its not exists");
		}

	}

	/**
	 * Updates course details by course ID.
	 *
	 * @param courseId             The ID of the course to update.
	 * @param updateCourseResponse The updated course details.
	 */
	public void updateCourseByCourseId(Long courseId, UpdateCourseResponse updateCourseResponse) {
		log.debug("Received request to update course with ID {}: {}", courseId, updateCourseResponse);

		Optional<Course> optCourse = courseRepository.findById(courseId);
		if (optCourse.isPresent()) {
			Course course = optCourse.get();
			EducationUtil.map(updateCourseResponse, course);
			log.info("Updating course: {}", course);
			courseRepository.save(course);
		} else {
			// will use custome exception
			log.info("Course with ID {} does not exist", courseId);
			throw new RuntimeException("Course with courseId " + courseId + " its not exists");
		}

	}

}

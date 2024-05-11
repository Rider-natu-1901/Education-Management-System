package com.project.education.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "course", schema = "education")
public class Course {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name="name")
	private String name;

	@Column(name="description")
	private String description;

	@Column(name="duration")
	private int duration;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "educationid")
	private Education education;

	@Override
	public String toString() {
		return "Course [id=" + id + ", name=" + name + ", description=" + description + ", duration=" + duration + "]";
	}

}

package com.project.education.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "education", schema = "education")
public class Education {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "percentagescored")
	private double percentageScored;

	@Column(name = "startyear")
	private int startYear;

	@Column(name = "endyear")
	private int endYear;

	@Column(name = "location")
	private String location;

	@Column(name = "type")
	private String type;

	@Column(name = "institutionname")
	private String institutionName;

	@Column(name = "degree")
	private String degree;

	@Column(name = "collegerank")
	private int collegeRank;

	@Column(name = "emailid")
	private String emailId;

	@Column(name = "studentname")
	private String studentname;
	
	@Column(name="createdtime")
	private LocalDateTime createdTime;

	@OneToMany(mappedBy = "education", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Course> courses;

	@Override
	public String toString() {
		return "Education [id=" + id + ", percentageScored=" + percentageScored + ", startYear=" + startYear
				+ ", endYear=" + endYear + ", location=" + location + ", type=" + type + ", institutionName="
				+ institutionName + ", degree=" + degree + ", collegeRank=" + collegeRank + ", emailId=" + emailId
				+ ", studentname=" + studentname + ", createdTime=" + createdTime + "]";
	}

}

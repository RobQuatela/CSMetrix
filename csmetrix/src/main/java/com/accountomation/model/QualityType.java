package com.accountomation.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="QualityTypes")
public class QualityType {
	
	private int id;
	private String name;
	private String description;
	private List<QualityScore> qualityScores;
	
	public QualityType() {
		
	}
	
	public QualityType(int id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public QualityType(String name, String description) {
		this.name = name;
		this.description = description;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="QTid")
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name="QTname")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@OneToMany(mappedBy = "qualityType", cascade = CascadeType.ALL)
	public List<QualityScore> getQualityScores() {
		return qualityScores;
	}

	public void setQualityScores(List<QualityScore> qualityScore) {
		this.qualityScores = qualityScore;
	}

	@Column(name = "QTdescription")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}

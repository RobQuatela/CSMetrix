package com.accountomation.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Scores")
public class Score {

	private int id;
	private String name;
	private List<QualityScore> qualityScores;
	
	public Score() {
		
	}
	
	public Score(int id) {
		this.id = id;
	}

	@Id
	@Column(name = "Sid")
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "Sname")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@OneToMany(mappedBy = "score", cascade = CascadeType.ALL,
			targetEntity = QualityScore.class)
	public List<QualityScore> getQualityScores() {
		return qualityScores;
	}

	public void setQualityScores(List<QualityScore> qualityScores) {
		this.qualityScores = qualityScores;
	}
	
	
}

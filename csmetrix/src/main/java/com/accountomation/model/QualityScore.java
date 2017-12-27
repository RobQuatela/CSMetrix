package com.accountomation.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "QualityScores")
public class QualityScore {

	private int id;
	private QualityType qualityType;
	private Score score;
	private List<RecordingQualityScore> recordingQualityScores = new ArrayList<>();
	
	public QualityScore() {
		
	}
	
	public QualityScore(int id, QualityType qualityType, Score score) {
		this.id = id;
		this.qualityType = qualityType;
		this.score = score;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "QSid")
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@ManyToOne(targetEntity = QualityType.class)
	@JoinColumn(name = "QSqualitytype", nullable = false,
		foreignKey = @ForeignKey(name = "fk_qualitytype_id"))
	public QualityType getQualityType() {
		return qualityType;
	}

	public void setQualityType(QualityType qualityType) {
		this.qualityType = qualityType;
	}

	@ManyToOne(targetEntity = Score.class)
	@JoinColumn(name = "QSscore", nullable = false,
		foreignKey = @ForeignKey(name = "fk_score_id"))
	public Score getScore() {
		return score;
	}

	public void setScore(Score score) {
		this.score = score;
	}

	@OneToMany(mappedBy = "qualityScore", cascade = CascadeType.ALL)
	public List<RecordingQualityScore> getRecordingQualityScores() {
		return recordingQualityScores;
	}

	public void setRecordingQualityScores(List<RecordingQualityScore> recordingQualityScores) {
		this.recordingQualityScores = recordingQualityScores;
	}
}

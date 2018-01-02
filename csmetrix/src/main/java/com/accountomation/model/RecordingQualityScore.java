package com.accountomation.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "RecordingQualityScores")
public class RecordingQualityScore {

	private long id;
	private Recording recording;
	private QualityScore qualityScore;
	
	public RecordingQualityScore() {
		
	}
	
	public RecordingQualityScore(Recording recording, QualityScore qualityScore) {
		this.recording = recording;
		this.qualityScore = qualityScore;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "RQSid")
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@ManyToOne(targetEntity = Recording.class)
	@JoinColumn(name = "RQSrecording", nullable = false,
		foreignKey = @ForeignKey(name = "fk_recordingqualityscore_recording"))
	public Recording getRecording() {
		return recording;
	}

	public void setRecording(Recording recording) {
		this.recording = recording;
	}

	@ManyToOne(targetEntity = QualityScore.class)
	@JoinColumn(name = "RQSqualityscore", nullable = false,
		foreignKey = @ForeignKey(name = "fk_recordingqualityscore_qualityscore"))
	public QualityScore getQualityScore() {
		return qualityScore;
	}

	public void setQualityScore(QualityScore qualityScore) {
		this.qualityScore = qualityScore;
	}
}

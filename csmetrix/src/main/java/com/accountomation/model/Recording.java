package com.accountomation.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Recordings")
public class Recording {

	private long id;
	private String name;
	private Date date;
	private Employee employee;
	private String notes;
	private List<RecordingQualityScore> recordingQualityScores = new ArrayList<>();
	
	public Recording() {
		
	}
	
	public Recording(Date date, String name, Employee employee, String notes) {
		this.date = date;
		this.name = name;
		this.employee = employee;
		this.notes = notes;
	}
	
	public Recording(Date date, String name, String notes) {
		this.date = date;
		this.name = name;
		this.notes = notes;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Rid")
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Column(name = "Rname")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "Rdate")
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@ManyToOne(targetEntity = Employee.class)
	@JoinColumn(name = "Remployee", nullable = false,
		foreignKey = @ForeignKey(name = "fk_recording_employee"))
	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	@Column(name = "Rnotes")
	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	@OneToMany(mappedBy = "recording", cascade = CascadeType.ALL)
	public List<RecordingQualityScore> getRecordingQualityScores() {
		return recordingQualityScores;
	}

	public void setRecordingQualityScores(List<RecordingQualityScore> recordingQualityScores) {
		this.recordingQualityScores = recordingQualityScores;
	}
	
	public void scoreRecording(QualityScore score) {
		RecordingQualityScore recordingQualityScore = new RecordingQualityScore(this, score);
		recordingQualityScores.add(recordingQualityScore);
	}
}

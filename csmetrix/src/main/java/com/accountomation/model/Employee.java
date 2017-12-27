package com.accountomation.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity 
@Table(name="Employees")
public class Employee {


	private String id;
	private String name;
	private String status;
	private String type;
	private List<Team> teams = new ArrayList<>();
	private List<EmployeeTeam> employeeTeams = new ArrayList<>();
	private List<Recording> recordings = new ArrayList<>();
	
	public Employee(String id, String name, String status, String type) {
		this.id = id;
		this.name = name;
		this.status = status;
		this.type = type;
	}

	@Id
	@Column(name="Eid", length = 255)
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name="Ename")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name="Estatus")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name="Etype")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@OneToMany(mappedBy = "lead", cascade = CascadeType.ALL,
			targetEntity = Team.class)
	public List<Team> getTeams() {
		return teams;
	}

	public void setTeams(List<Team> teams) {
		this.teams = teams;
	}
	
	public void makeTeamLead(Team team) {
		team.setLead(this);
		teams.add(team);
	}

	@OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
	public List<EmployeeTeam> getEmployeeTeams() {
		return employeeTeams;
	}

	public void setEmployeeTeams(List<EmployeeTeam> employeeTeams) {
		this.employeeTeams = employeeTeams;
	}

	@OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
	public List<Recording> getRecordings() {
		return recordings;
	}

	public void setRecordings(List<Recording> recordings) {
		this.recordings = recordings;
	}
	
	public void addToRecording(Recording recording) {
		recording.setEmployee(this);
		recordings.add(recording);
	}
}

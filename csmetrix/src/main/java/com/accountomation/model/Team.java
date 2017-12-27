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
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="Teams")
public class Team {

	private int id;
	private String name;
	private String status;
	private Employee lead;
	private TeamStart start;
	private TeamStop stop;
	private List<EmployeeTeam> employeeTeam = new ArrayList<>();
	
	public Team(String name, String status, Employee lead, TeamStart start, TeamStop stop) {
		this.name = name;
		this.status = status;
		this.lead = lead;
		this.start = start;
		this.stop = stop;
	}

	public Team() {

	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="Tid")
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name="Tname")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name="Tstatus")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@ManyToOne(targetEntity = Employee.class)
	@JoinColumn(name="Tlead", nullable = false, 
		foreignKey = @ForeignKey(name = "fk_employee_lead"))
	public Employee getLead() {
		return lead;
	}

	public void setLead(Employee lead) {
		this.lead = lead;
	}

	@OneToOne(mappedBy = "team", cascade = CascadeType.ALL, 
			targetEntity = TeamStart.class)
	@JoinColumn(name = "TSid")
	public TeamStart getStart() {
		return start;
	}

	public void setStart(TeamStart start) {
		this.start = start;
		start.setTeam(this);
	}

	@OneToOne(mappedBy = "team", cascade = CascadeType.ALL,
			targetEntity = TeamStop.class)
	@JoinColumn(name = "TSid")
	public TeamStop getStop() {
		return stop;
	}

	public void setStop(TeamStop stop) {
		this.stop = stop;
		stop.setTeam(this);
	}

	@OneToMany(mappedBy = "team", cascade = CascadeType.ALL)
	public List<EmployeeTeam> getEmployeeTeam() {
		return employeeTeam;
	}

	public void setEmployeeTeam(List<EmployeeTeam> employeeTeam) {
		this.employeeTeam = employeeTeam;
	}
	
	public void addMember(Employee employee) {
		EmployeeTeam employeeTeam = new EmployeeTeam();
		employeeTeam.setTeam(this);
		employeeTeam.setEmployee(employee);
		this.employeeTeam.add(employeeTeam);
	}
}

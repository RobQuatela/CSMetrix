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
@Table(name = "EmployeeTeams")
public class EmployeeTeam {

	private int id;
	private Employee employee;
	private Team team;
	
	public EmployeeTeam() {
		
	}
	
	public EmployeeTeam(int id, Employee employee, Team team) {
		this.id = id;
		this.employee = employee;
		this.team = team;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ETid")
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@ManyToOne(targetEntity = Employee.class)
	@JoinColumn(name = "ETemployee", nullable = false,
		foreignKey = @ForeignKey(name = "fk_employee_id"))
	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	@ManyToOne(targetEntity = Team.class)
	@JoinColumn(name = "ETteam", nullable = false,
		foreignKey = @ForeignKey(name = "fk_team_id"))
	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}
}

package com.accountomation.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.hibernate.annotations.Parameter;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "TeamStart")
public class TeamStart {

	private int id;
	private Team team;
	private Date start;
	
	public TeamStart() {
		
	}
	
	public TeamStart(int id, Team team, Date start) {
		this.id = id;
		this.team = team;
		this.start = start;
	}

	@Id
	@GeneratedValue(generator = "gen")
	@GenericGenerator(name = "gen", strategy = "foreign", 
		parameters = {@Parameter(name = "property", value = "team")})
	@Column(name = "TSid")
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@OneToOne(targetEntity = Team.class)
	@JoinColumn(foreignKey = @ForeignKey(name = "fk_team_start"))
	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

	@Column(name = "TSdate")
	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}
}

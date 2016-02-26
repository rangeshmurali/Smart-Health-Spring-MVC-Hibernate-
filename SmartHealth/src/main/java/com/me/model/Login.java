package com.me.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Login")
public class Login implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@Id 
	@Column(name = "USERNAME")
	private String userName;
	
	@Column(name = "USERPASSWORD")
	private String userPassword;
	
	@Column(name = "ROLE")
	private String role;
	
	@Column(name = "SECURITYQUESTION")
	private String securityQuestion;
	
	@Column(name = "SECURITYANSWER")
	private String securityAnswer;
	
	@OneToOne(mappedBy="login")
	private SmartId smartId;
	
	@OneToOne(mappedBy="doctorLogin")
	private Doctor doctor;
	
	@OneToOne(mappedBy="managerLogin")
	private HospitalFinanceManager hospitalFinanceManager;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public SmartId getSmartId() {
		return smartId;
	}

	public void setSmartId(SmartId smartId) {
		this.smartId = smartId;
	}

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	public HospitalFinanceManager getHospitalFinanceManager() {
		return hospitalFinanceManager;
	}

	public void setHospitalFinanceManager(HospitalFinanceManager hospitalFinanceManager) {
		this.hospitalFinanceManager = hospitalFinanceManager;
	}

	public String getSecurityQuestion() {
		return securityQuestion;
	}

	public void setSecurityQuestion(String securityQuestion) {
		this.securityQuestion = securityQuestion;
	}

	public String getSecurityAnswer() {
		return securityAnswer;
	}

	public void setSecurityAnswer(String securityAnswer) {
		this.securityAnswer = securityAnswer;
	}
	
	
	
	
	
	
	

}

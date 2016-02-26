package com.me.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "REPORT")
public class Report implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	@Column(name = "REPORTID")
	private int reportId;
	
	@Column(name = "GENERATEDDATE")
	private String generatedDate;
	
	@Column(name = "DESCRIPTION")
	private String description;
	
	@Column(name = "SEVERITY")
	private String severity;
	
	@Column(name = "REQUIREDMEDICALTEST")
	private String requiredMedicalTest;
	
	@Column(name = "PRESCRIBEDMEDICINE")
	private String prescribedMedicine;
	
	@Column(name = "ASSIGNEDFINANCEMANAGER")
	private String assignedFinanceManager;
	
	@Column(name = "GENERATED")
	private String generated;
	
	@ManyToOne
	@JoinColumn(name="SMARTID")
	private SmartId smartId;
	
	@ManyToOne
	@JoinColumn(name="EMPLOYEEID")
	private Doctor doctor;

	public int getReportId() {
		return reportId;
	}

	public void setReportId(int reportId) {
		this.reportId = reportId;
	}

	public String getGeneratedDate() {
		return generatedDate;
	}

	public void setGeneratedDate(String generatedDate) {
		this.generatedDate = generatedDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getSeverity() {
		return severity;
	}

	public void setSeverity(String severity) {
		this.severity = severity;
	}

	public String getRequiredMedicalTest() {
		return requiredMedicalTest;
	}

	public void setRequiredMedicalTest(String requiredMedicalTest) {
		this.requiredMedicalTest = requiredMedicalTest;
	}

	public String getPrescribedMedicine() {
		return prescribedMedicine;
	}

	public void setPrescribedMedicine(String prescribedMedicine) {
		this.prescribedMedicine = prescribedMedicine;
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

	public String getAssignedFinanceManager() {
		return assignedFinanceManager;
	}

	public void setAssignedFinanceManager(String assignedFinanceManager) {
		this.assignedFinanceManager = assignedFinanceManager;
	}

	public String getGenerated() {
		return generated;
	}

	public void setGenerated(String generated) {
		this.generated = generated;
	}
	
	

	
	
	
	
	

}

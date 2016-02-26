package com.me.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "HOSPITALFINANCEMANAGER")
public class HospitalFinanceManager implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	@Column(name = "EMPLOYEEID")
	private int employeeId;
	
	@Column(name = "EMPLOYEENAME")
	private String employeeName;
	
	@Column(name = "AGE")
	private int age;
	
	@Column(name = "STREET")
	private String street;
	
	@Column(name = "CITY")
	private String city;
	
	@Column(name = "ZIP")
	private String zip;
	
	@Column(name = "STATE")
	private String state;
	
	@Column(name = "QUALIFICATION")
	private String qualification;
	
	@Column(name = "EMAILID")
	private String emailId;
	
	@Column(name = "PHONENUMBER")
	private String phoneNo;
	
	@ManyToOne
	@JoinColumn(name="HOSPITALID")
	private Hospital hospital;
	
	@OneToOne
	@JoinColumn(name = "USERNAME")
	private Login managerLogin;
	
	@OneToMany(mappedBy="financeManager")
	private List<Invoice> invoiceList = new ArrayList<Invoice>();

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getQualification() {
		return qualification;
	}

	public void setQualification(String qualification) {
		this.qualification = qualification;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public Hospital getHospital() {
		return hospital;
	}

	public void setHospital(Hospital hospital) {
		this.hospital = hospital;
	}

	public Login getManagerLogin() {
		return managerLogin;
	}

	public void setManagerLogin(Login managerLogin) {
		this.managerLogin = managerLogin;
	}

	public List<Invoice> getInvoiceList() {
		return invoiceList;
	}

	public void setInvoiceList(List<Invoice> invoiceList) {
		this.invoiceList = invoiceList;
	}
	
	
	
	
	
	


}

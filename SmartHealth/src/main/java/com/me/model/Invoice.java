package com.me.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "INVOICE")
public class Invoice implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	@Column(name = "INVOICEID")
	private int invoiceId;
	
	@Column(name = "DATE")
	private String date;
	
	@Column(name = "TOTALAMOUNT")
	private float totalAmount;
	
	@Column(name = "INVOICEDESCRIPTION")
	private String invoiceDescription;
	
	@Column(name = "REPORTID")
	private int reportId;
	
	@Column(name = "DOCTORID")
	private int doctorId;
	
	@Column(name = "HOSPITALID")
	private int hospitalId;
	
	@Column(name = "PAID")
	private String paid;
	
	@ManyToOne
	@JoinColumn(name="SMARTID")
	private SmartId smartId;
	
	@ManyToOne
	@JoinColumn(name="EMPLOYEEID")
	private HospitalFinanceManager financeManager;

	public int getInvoiceId() {
		return invoiceId;
	}

	public void setInvoiceId(int invoiceId) {
		this.invoiceId = invoiceId;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public float getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(float totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getInvoiceDescription() {
		return invoiceDescription;
	}

	public void setInvoiceDescription(String invoiceDescription) {
		this.invoiceDescription = invoiceDescription;
	}

	public int getReportId() {
		return reportId;
	}

	public void setReportId(int reportId) {
		this.reportId = reportId;
	}

	public int getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(int doctorId) {
		this.doctorId = doctorId;
	}

	public int getHospitalId() {
		return hospitalId;
	}

	public void setHospitalId(int hospitalId) {
		this.hospitalId = hospitalId;
	}

	public SmartId getSmartId() {
		return smartId;
	}

	public void setSmartId(SmartId smartId) {
		this.smartId = smartId;
	}

	public HospitalFinanceManager getFinanceManager() {
		return financeManager;
	}

	public void setFinanceManager(HospitalFinanceManager financeManager) {
		this.financeManager = financeManager;
	}

	public String getPaid() {
		return paid;
	}

	public void setPaid(String paid) {
		this.paid = paid;
	}
	
	
	

}

package com.me.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "DAILYRECORDS")
public class DailyRecord implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	@Column(name = "DRECORDID")
	private int dRecordId;
	
	@Column(name = "DATE")
	private String date;
	
	@Column(name = "WALKINGDISTANCE")
	private float walkingDistance;
	
	@Column(name = "RUNNINGDISTANCE")
	private float runningDistance;
	
	@Column(name = "FLIGHTSCLIMED")
	private int flightsClimed;
	
	@Column(name = "ACTIVECALORIES")
	private float activeCalories;
	
	@Column(name = "BLOODPRESSURE")
	private String bloodPressure;
	
	@Column(name = "HEARTRATE")
	private int heartRate;
	
	@Column(name = "SMARTID")
	private int smartId;

	public int getdRecordId() {
		return dRecordId;
	}

	public void setdRecordId(int dRecordId) {
		this.dRecordId = dRecordId;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public float getWalkingDistance() {
		return walkingDistance;
	}

	public void setWalkingDistance(float walkingDistance) {
		this.walkingDistance = walkingDistance;
	}

	public float getRunningDistance() {
		return runningDistance;
	}

	public void setRunningDistance(float runningDistance) {
		this.runningDistance = runningDistance;
	}

	public int getFlightsClimed() {
		return flightsClimed;
	}

	public void setFlightsClimed(int flightsClimed) {
		this.flightsClimed = flightsClimed;
	}

	public float getActiveCalories() {
		return activeCalories;
	}

	public void setActiveCalories(float activeCalories) {
		this.activeCalories = activeCalories;
	}

	public String getBloodPressure() {
		return bloodPressure;
	}

	public void setBloodPressure(String bloodPressure) {
		this.bloodPressure = bloodPressure;
	}

	public int getHeartRate() {
		return heartRate;
	}

	public void setHeartRate(int heartRate) {
		this.heartRate = heartRate;
	}

	public int getSmartId() {
		return smartId;
	}

	public void setSmartId(int smartId) {
		this.smartId = smartId;
	}
	
	

}

package com.me.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "MONTHLYRECORD")
public class MonthlyRecord implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	@Column(name = "MRECORDID")
	private int mRecordId;
	
	@Column(name = "MONTH")
	private String month;
	
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
	
	@Column(name = "ASSIGN")
	private String assign;
	
	@Column(name = "GENERATED")
	private String generated;

	public int getmRecordId() {
		return mRecordId;
	}

	public void setmRecordId(int mRecordId) {
		this.mRecordId = mRecordId;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
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

	public String getGenerated() {
		return generated;
	}

	public void setGenerated(String generated) {
		this.generated = generated;
	}

	public String getAssign() {
		return assign;
	}

	public void setAssign(String assign) {
		this.assign = assign;
	}
	
	
	
	
	

}

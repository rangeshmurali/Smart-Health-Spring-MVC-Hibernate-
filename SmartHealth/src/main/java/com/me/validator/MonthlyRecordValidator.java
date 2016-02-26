package com.me.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.me.model.DailyRecord;
import com.me.model.MonthlyRecord;

public class MonthlyRecordValidator implements Validator {
	
	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return MonthlyRecord.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		MonthlyRecord monthlyRecord = (MonthlyRecord)target; 
		 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "month", "validate.month", "Month should not be empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "runningDistance", "validate.runningDistance", "Running Distance should not be empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "flightsClimed", "validate.flightsClimed", "Flights Climed should not be empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "activeCalories", "validate.activeCalories", "Active Calories should not be empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "bloodPressure", "validate.bloodPressure", "Blood Pressure should not be empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "heartRate", "validate.heartRate", "Heart Rate should not be empty");
	}

}

package com.me.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.me.model.DailyRecord;
import com.me.model.SmartId;

public class SmartIdValidator implements Validator {
	
	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return SmartId.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		SmartId smartId = (SmartId)target; 
		 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "validate.firstName", "Fist Name should not be empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "validate.lastName", "Last Name should not be empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "age", "validate.age", "Age should not be empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "street", "validate.street", "Street should not be empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "city", "validate.city", "City should not be empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "zip", "validate.zip", "ZIP should not be empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "state", "validate.state", "State should not be empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dateOfBirth", "validate.dateOfBirth", "Date of Birth should not be empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "gender", "validate.gender", "Gender should not be empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "emailId", "validate.emailId", "EmailId should not be empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "phoneNo", "validate.phoneNo", "Phone Number should not be empty");
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "bloodGroup", "validate.bloodGroup", "Blood group should not be empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "height", "validate.height", "Height should not be empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "weight", "validate.weight", "Weight should not be empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "emergencyContactName", "validate.emergencyContactName", "Emergency Contact Name should not be empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "emergencyContactPhoneNo", "validate.emergencyContactPhoneNo", "Emergency Contact PhoneNo should not be empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "priscribingMedicine", "validate.priscribingMedicine", "Priscribing Medicine should not be empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "medicalTestTook", "validate.medicalTestTook", "Medical Test Took should not be empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "allergies", "validate.allergies", "Allergies should not be empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "medicalHistoryDecription", "validate.medicalHistoryDecription", "Medical History Decription should not be empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "vitalSignInfo", "validate.vitalSignInfo", "VitalSignInfo should not be empty");
	}

}

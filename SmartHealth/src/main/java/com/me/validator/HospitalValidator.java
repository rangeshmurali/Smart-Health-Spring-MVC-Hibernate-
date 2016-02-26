package com.me.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.me.model.DailyRecord;
import com.me.model.Hospital;

public class HospitalValidator implements Validator {
	
	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return Hospital.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		Hospital hospital = (Hospital)target; 
		 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "hospitalName", "validate.hospitalName", "Hospital Name should not be empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "street", "validate.street", "Street should not be empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "city", "validate.city", "City should not be empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "zip", "validate.zip", "ZIP should not be empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "state", "validate.state", "State should not be empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "qualification", "validate.qualification", "Qualification should not be empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "emailId", "validate.emailId", "EmailId should not be empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "phoneNo", "validate.phoneNo", "Phone Number should not be empty");
	}

}

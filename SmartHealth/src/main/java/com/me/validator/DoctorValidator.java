package com.me.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.me.model.DailyRecord;
import com.me.model.Doctor;

public class DoctorValidator implements Validator {
	
	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return Doctor.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		Doctor doctor = (Doctor)target; 
		 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "employeeName", "validate.employeeName", "Employee Name should not be empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "age", "validate.age", "Age should not be empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "street", "validate.street", "Street should not be empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "city", "validate.city", "City should not be empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "zip", "validate.zip", "ZIP should not be empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "state", "validate.state", "State should not be empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "qualification", "validate.qualification", "Qualification should not be empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "emailId", "validate.emailId", "EmailId should not be empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "phoneNo", "validate.phoneNo", "Phone Number should not be empty");
	}

}

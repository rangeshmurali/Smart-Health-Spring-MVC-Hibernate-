package com.me.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.me.model.Login;


public class LoginValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return Login.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		Login login = (Login)target; 
		 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userName", "validate.userName", "UserName should not be empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userPassword", "validate.userPassword", "Password should not be empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "securityQuestion", "validate.securityQuestion", "Security Question should not be empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "securityAnswer", "validate.securityAnswer", "Security Answer should not be empty");
		
	}
	
	

}

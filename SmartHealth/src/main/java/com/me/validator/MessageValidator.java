package com.me.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.me.model.DailyRecord;
import com.me.model.Message;

public class MessageValidator implements Validator {
	
	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return Message.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		Message message = (Message)target; 
		 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userName", "validate.userName", "To User should not be empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "fromUser", "validate.fromUser", "User Name should not be empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "message", "validate.message", "Message should not be empty");
	}

}

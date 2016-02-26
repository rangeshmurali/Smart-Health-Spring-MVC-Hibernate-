package com.me.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.me.model.DailyRecord;
import com.me.model.Report;

public class ReportValidator implements Validator {
	
	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return Report.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		Report report = (Report)target; 
		 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "validate.description", "Description should not be empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "severity", "validate.severity", "Severity should not be empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "requiredMedicalTest", "validate.requiredMedicalTest", "Required Medical Test should not be empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "prescribedMedicine", "validate.prescribedMedicine", "Prescribed Medicine should not be empty");
	}

}

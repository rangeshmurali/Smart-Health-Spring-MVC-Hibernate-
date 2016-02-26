package com.me.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.me.model.DailyRecord;
import com.me.model.Invoice;

public class InvoiceValidator implements Validator {
	
	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return Invoice.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		Invoice invoice = (Invoice)target; 
		 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "date", "validate.date", "Date should not be empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "totalAmount", "validate.totalAmount", "Total Amount should not be empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "invoiceDescription", "validate.invoiceDescription", "Invoice Description should not be empty");
	}

}

package a.b.c.controller;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import a.b.c.model.CommandAdminLogin;

public class AdminLoginValidator implements Validator{
	@Override
	public boolean supports(Class<?> arg0) {
		return CommandAdminLogin.class.isAssignableFrom(arg0);
	}
	
	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "adm_id", "required");
		ValidationUtils.rejectIfEmpty(errors, "adm_pass", "required");
	}

}

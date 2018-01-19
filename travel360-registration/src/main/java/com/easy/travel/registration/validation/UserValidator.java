package com.easy.travel.registration.validation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.easy.travel.registration.model.RegistrationDataModel;
import com.easy.travel.registration.service.RegistrationService;

@Component
public class UserValidator implements Validator {
    @Autowired
    private RegistrationService flightService;

    @Override
    public boolean supports(Class<?> aClass) {
        return RegistrationDataModel.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
    	RegistrationDataModel lowFareSearch = (RegistrationDataModel) o;

    	/*ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "NotEmpty");
        if (lowFareSearch.getFirstName().length() < 6) {
            errors.rejectValue("firstName", "Size.userForm.firstName");
        }*/
       /* if (userService.findByUsername(user.getUsername()) != null) {
            errors.rejectValue("username", "Duplicate.userForm.username");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty");
        if (user.getPassword().length() < 8 || user.getPassword().length() > 32) {
            errors.rejectValue("password", "Size.userForm.password");
        }

        if (!user.getPasswordConfirm().equals(user.getPassword())) {
            errors.rejectValue("passwordConfirm", "Diff.userForm.passwordConfirm");
        }*/
    }
}


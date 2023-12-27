package com.company.authservice.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.passay.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class PasswordConstraintValidator implements ConstraintValidator<ValidPassword, String> {

    @Override
    public void initialize(ValidPassword constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(
            String password,
            ConstraintValidatorContext constraintValidatorContext) {

        Properties property = new Properties();

        InputStream inputStream = getClass().getClassLoader()
                .getResourceAsStream(
                        "passay.properties"
                );

        try {

            property.load(inputStream);

        } catch (IOException exception) {

            throw new RuntimeException();

        }

        PasswordValidator validator = getPasswordValidator(property);

        RuleResult result = validator.validate(new PasswordData(password));

        if (result.isValid()) {
            return true;
        }

        List<String> message = validator.getMessages(result);
        String messageTemplate = String.join(",", message);
        constraintValidatorContext
                .buildConstraintViolationWithTemplate(messageTemplate)
                .addConstraintViolation()
                .disableDefaultConstraintViolation();

        return false;
        
    }

    private static PasswordValidator getPasswordValidator(Properties property) {

        MessageResolver resolver = new PropertiesMessageResolver(property);

        return new PasswordValidator(resolver, Arrays.asList(
                new LengthRule(8, 16),
                new CharacterRule(EnglishCharacterData.UpperCase, 1),
                new CharacterRule(EnglishCharacterData.LowerCase, 1),
                new CharacterRule(EnglishCharacterData.Digit, 1),
                new CharacterRule(EnglishCharacterData.Special, 1),
                new WhitespaceRule(),
                new IllegalSequenceRule(EnglishSequenceData.Alphabetical, 5, false),
                new IllegalSequenceRule(EnglishSequenceData.Numerical, 5, false)
        ));

    }
}

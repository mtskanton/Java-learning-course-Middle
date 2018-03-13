package app.example.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Правила валидации пароля.
 */
public class PasswordValidator implements ConstraintValidator<PasswordConstraint, String> {

    @Override
    public void initialize(PasswordConstraint contactNumber) {
    }

//  (?=.*\d) Должен содержать цифру от 0 до 9
//  (?=.*[a-z]) Должен содержать символ латинницы в нижем регистре
//  (?=.*[A-Z]) Должен содержать символ латинницы в верхнем регистре
//  (?=.*[@#$%]) Должен содержать специальный символ из списка "@#$%"
    @Override
    public boolean isValid(String contactField, ConstraintValidatorContext cxt) {
        return contactField != null
                && contactField.matches("^((?=.*\\d)(?=.*[a-z])).*$")
                && (contactField.length() > 3);
    }
}

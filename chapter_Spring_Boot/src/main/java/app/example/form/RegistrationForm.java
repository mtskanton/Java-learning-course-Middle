package app.example.form;

import app.example.validation.FieldsValueMatch;
import app.example.validation.PasswordConstraint;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.AssertTrue;

/**
 * Форма регистрации.
 */
@FieldsValueMatch(
        field = "password",
        fieldMatch = "verifyPassword",
        message = "пароли не совпадают"
)
public class RegistrationForm {

    @NotEmpty(message = "поле не может быть пустым")
    private String name;

    @Email
    @NotEmpty(message = "поле не может быть пустым")
    private String email;

    @PasswordConstraint(message = "пароль должен быть более 3 символов, содержать цифры и латинские буквы")
    @NotEmpty(message = "поле не может быть пустым")
    private String password;

    private String verifyPassword;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getVerifyPassword() {
        return verifyPassword;
    }

    public void setVerifyPassword(String verifyPassword) {
        this.verifyPassword = verifyPassword;
    }
}

package br.com.eco.userservice.validators;

import br.com.eco.userservice.domains.User;
import br.com.eco.userservice.domains.UserRepository;
import br.com.eco.userservice.exceptions.CustomException;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import java.util.Map;

@Component
@Log4j2
public class UserValidator {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    public void checkIsEmailExists(String email) {
        log.info("Validating if e-mail is valid.");
        if (userRepository.findByEmail(email).isPresent()) {
            throw new CustomException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "The e-mail " + email + " is already in use."
            );
        }
        log.info("Finishing e-mail validation.");
    }

    public void checkPasswordLengthIsValid(String password) {
        log.info("Validating if password is valid.");
        if (password.length() < 4 || password.length() > 8) {
            throw new CustomException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "The password is invalid length."
            );
        }
        log.info("Finishing password validation.");
    }

    public void encodingPassword(User user) {
        log.info("Starting encrypt password.");
        var password = user.getPassword();
        user.setPassword(passwordEncoder.encode(password));
        log.info("Finishing encoding password.");
    }

    public void findUserFields(User user, Map<Object, Object> fieldsUser) {
        log.info("Searching user fields.");
        if (fieldsUser.isEmpty()) return;
        fieldsUser.forEach((key, value) -> {
            var field = ReflectionUtils.findField(User.class, (String) key);
            if (field == null)
                throw new CustomException(
                        HttpStatus.NOT_FOUND, "Field not found."
                );
            field.setAccessible(true);
            if (field.getName().equals("password")) encodingPassword(user);
            ReflectionUtils.setField(field, user, user.getPassword());
        });
        log.info("Finishing searching user fields.");
    }
}

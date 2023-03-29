package br.com.eco.userservice.services;

import br.com.eco.userservice.domains.User;
import br.com.eco.userservice.domains.UserRepository;
import br.com.eco.userservice.domains.UserResponse;
import br.com.eco.userservice.exceptions.CustomException;
import br.com.eco.userservice.mappers.UserMapper;
import br.com.eco.userservice.validators.UserValidator;
import jakarta.persistence.EntityManager;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@Log4j2
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserValidator userValidator;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private EntityManager entityManager;

    public UserResponse registerUser(User user) {
        log.info("Starting the register user flow.");
        userValidator.checkIsEmailExists(user.getEmail());
        userValidator.checkPasswordLengthIsValid(user.getPassword());
        userValidator.encodingPassword(user);
        log.info("Saving user in database.");
        var userSaved = userRepository.save(user);
        log.info("Initializing mapping from user.");
        var userResponse = userMapper.fromUser(userSaved);
        log.info("Finishing the register user flow.");
        return userResponse;
    }

    public UserResponse updateUser(Long userId, Map<Object, Object> fieldsUser) {
        log.info("Starting the update user flow.");
        var user = userRepository.findById(userId).orElseThrow(
                () -> new CustomException(HttpStatus.NOT_FOUND, "User not found.")
        );
        userValidator.findUserFields(user, fieldsUser);
        log.info("Saving user in database.");
        var userSaved = userRepository.save(user);
        log.info("Initializing mapping from user.");
        var userResponse = userMapper.fromUser(userSaved);
        log.info("Finishing the update user flow.");
        return userResponse;
    }
}

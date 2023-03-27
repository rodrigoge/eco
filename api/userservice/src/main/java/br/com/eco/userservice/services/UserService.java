package br.com.eco.userservice.services;

import br.com.eco.userservice.domains.User;
import br.com.eco.userservice.domains.UserRepository;
import br.com.eco.userservice.mappers.UserMapper;
import br.com.eco.userservice.to.UserTO;
import br.com.eco.userservice.validators.UserValidator;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserValidator userValidator;

    @Autowired
    private UserMapper userMapper;

    public UserTO registerUser(User user) {
        log.info("Starting the register user flow.");
        userValidator.checkIsEmailExists(user.getEmail());
        userValidator.checkPasswordLengthIsValid(user.getPassword());
        userValidator.encodingPassword(user);
        log.info("Saving user in database.");
        var userResponse = userRepository.save(user);
        log.info("Initializing mapping from user.");
        var userTO = userMapper.fromUser(userResponse);
        log.info("Finishing the register user flow.");
        return userTO;
    }

    public List<UserTO> getUsers() {
        log.info("Starting the get users flow.");
        var users = userRepository.findAll();
        var usersTO = buildUsers(users);
        log.info("Finishing the get users flow.");
        return usersTO;
    }

    public List<UserTO> buildUsers(List<User> users) {
        log.info("Initializing mapping from users.");
        return users
                .stream()
                .map(userMapper::fromUser)
                .collect(Collectors.toList());
    }
}

package br.com.eco.userservice.api;

import br.com.eco.userservice.domains.User;
import br.com.eco.userservice.services.UserService;
import br.com.eco.userservice.to.UserTO;
import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Log4j2
@RequestMapping("/users")
public class UserControllerApi {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserTO> register(@Valid @RequestBody User user) {
        log.info("Initializing request for register user.");
        var response = userService.registerUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}

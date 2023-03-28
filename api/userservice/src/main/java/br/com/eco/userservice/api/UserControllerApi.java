package br.com.eco.userservice.api;

import br.com.eco.userservice.domains.User;
import br.com.eco.userservice.domains.UserRequest;
import br.com.eco.userservice.enums.UserSortByEnum;
import br.com.eco.userservice.services.UserService;
import br.com.eco.userservice.domains.UserResponse;
import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Log4j2
@RequestMapping("/users")
public class UserControllerApi {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@Valid @RequestBody User user) {
        log.info("Initializing request for register user.");
        var response = userService.registerUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/list")
    public ResponseEntity<List<UserResponse>> getUsers(String name,
                                                       String email,
                                                       Integer offset,
                                                       Integer limit,
                                                       String sort,
                                                       String order) {
        log.info("Initializing request for get users.");
        var request = UserRequest
                .builder()
                .name(name)
                .email(email)
                .limit(limit)
                .offset(offset)
                .build();
        var response = userService.getUsers(request);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}

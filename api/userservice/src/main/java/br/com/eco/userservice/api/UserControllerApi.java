package br.com.eco.userservice.api;

import br.com.eco.userservice.domains.User;
import br.com.eco.userservice.domains.UserRequest;
import br.com.eco.userservice.domains.UserResponse;
import br.com.eco.userservice.domains.UsersResponse;
import br.com.eco.userservice.services.GetUserService;
import br.com.eco.userservice.services.UserService;
import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@Log4j2
@RequestMapping("/users")
public class UserControllerApi {

    @Autowired
    private UserService userService;

    @Autowired
    private GetUserService getUserService;

    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@Valid @RequestBody User user) {
        log.info("Initializing request for register user.");
        var response = userService.registerUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PatchMapping("/{userId}")
    public ResponseEntity<UserResponse> update(@PathVariable Long userId,
                                               @RequestBody Map<Object, Object> fieldsUser) {
        log.info("Initializing request for update user.");
        var response = userService.updateUser(userId, fieldsUser);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<String> remove(@PathVariable Long userId) {
        log.info("Initializing request for remove user.");
        userService.removeUser(userId);
        return ResponseEntity.status(HttpStatus.OK).body("User removed with successfully.");
    }

    @GetMapping("/list")
    public ResponseEntity<UsersResponse> getUsers(@RequestParam(required = false) String name,
                                                  @RequestParam(required = false) String email,
                                                  @RequestParam Integer offset,
                                                  @RequestParam Integer limit,
                                                  @RequestParam String sort,
                                                  @RequestParam String order) {
        log.info("Initializing request for get users.");
        var request = UserRequest
                .builder()
                .name(name)
                .email(email)
                .limit(limit)
                .offset(offset)
                .sortByEnum(sort)
                .orderByEnum(order)
                .build();
        var response = getUserService.getUsers(request);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}

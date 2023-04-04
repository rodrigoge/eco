package br.com.eco.pointservice.api;

import br.com.eco.pointservice.enums.StateEnum;
import br.com.eco.pointservice.services.GetStateService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Log4j2
@RequestMapping("/states")
public class StateControllerApi {

    @Autowired
    private GetStateService getStateService;

    @GetMapping
    public ResponseEntity<List<StateEnum>> getStates() {
        log.info("Initializing request for get all states.");
        var response = getStateService.getStates();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}

package br.com.eco.pointservice.services;

import br.com.eco.pointservice.enums.StateEnum;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
public class GetStateService {

    public List<StateEnum> getStates() {
        log.info("Starting get all states flow.");
        var states = StateEnum.values();
        log.info("Finishing get all states flow.");
        return List.of(states);
    }
}

package br.com.eco.pointservice.api;

import br.com.eco.pointservice.domains.PointRequest;
import br.com.eco.pointservice.domains.PointsResponse;
import br.com.eco.pointservice.services.GetPointService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Log4j2
@RequestMapping("/points")
public class PointControllerApi {

    @Autowired
    private GetPointService getPointService;

    @GetMapping("/list")
    public ResponseEntity<PointsResponse> getPoints(@RequestParam(required = false) String name,
                                                    @RequestParam(required = false) String email,
                                                    @RequestParam(required = false) String city,
                                                    @RequestParam(required = false) String uf,
                                                    @RequestParam(required = false) String address,
                                                    @RequestParam Integer offset,
                                                    @RequestParam Integer limit,
                                                    @RequestParam String sort,
                                                    @RequestParam String order) {
        log.info("Initializing request for get points.");
        var request = PointRequest
                .builder()
                .name(name)
                .email(email)
                .city(city)
                .uf(uf)
                .address(address)
                .limit(limit)
                .offset(offset)
                .sortByEnum(sort)
                .orderByEnum(order)
                .build();
        var response = getPointService.getPoints(request);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}

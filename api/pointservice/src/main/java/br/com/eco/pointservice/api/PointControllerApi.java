package br.com.eco.pointservice.api;

import br.com.eco.pointservice.domains.Point;
import br.com.eco.pointservice.domains.PointRequest;
import br.com.eco.pointservice.domains.PointResponse;
import br.com.eco.pointservice.domains.PointsResponse;
import br.com.eco.pointservice.services.GetPointService;
import br.com.eco.pointservice.services.PointService;
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
@RequestMapping("/points")
public class PointControllerApi {

    @Autowired
    private GetPointService getPointService;

    @Autowired
    private PointService pointService;

    @PostMapping("/register")
    public ResponseEntity<PointResponse> register(@Valid @RequestBody Point point) {
        log.info("Initializing request for register point.");
        var response = pointService.registerPoint(point);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PatchMapping("/{pointId}")
    public ResponseEntity<PointResponse> update(@PathVariable Long pointId,
                                                @RequestBody Map<Object, Object> fieldsPoint) {
        log.info("Initializing request for update point.");
        var response = pointService.updatePoint(pointId, fieldsPoint);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/{pointId}")
    public ResponseEntity<String> remove(@PathVariable Long pointId) {
        log.info("Initializing request for remove point.");
        pointService.removePoint(pointId);
        return ResponseEntity.status(HttpStatus.OK).body("Point removed with successfully.");
    }

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

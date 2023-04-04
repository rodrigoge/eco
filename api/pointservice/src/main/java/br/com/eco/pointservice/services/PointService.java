package br.com.eco.pointservice.services;

import br.com.eco.pointservice.domains.Point;
import br.com.eco.pointservice.domains.PointRepository;
import br.com.eco.pointservice.domains.PointResponse;
import br.com.eco.pointservice.exceptions.CustomException;
import br.com.eco.pointservice.mappers.PointMapper;
import br.com.eco.pointservice.validators.PointValidator;
import jakarta.persistence.EntityManager;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@Log4j2
public class PointService {

    @Autowired
    private PointRepository pointRepository;

    @Autowired
    private PointMapper pointMapper;

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private PointValidator pointValidator;

    public PointResponse registerPoint(Point point) {
        log.info("Starting the register point flow.");
        log.info("Saving point in database.");
        var pointSaved = pointRepository.save(point);
        log.info("Initializing mapping from point.");
        var pointResponse = pointMapper.fromPoint(pointSaved);
        log.info("Finishing the register point flow.");
        return pointResponse;
    }

    public PointResponse updatePoint(Long pointId, Map<Object, Object> fieldsPoint) {
        log.info("Starting the update point flow.");
        var point = pointRepository.findById(pointId).orElseThrow(
                () -> new CustomException(HttpStatus.NOT_FOUND, "Point not found.")
        );
        pointValidator.findPointFields(point, fieldsPoint);
        log.info("Saving point in database.");
        var pointSaved = pointRepository.save(point);
        log.info("Initializing mapping from point.");
        var pointResponse = pointMapper.fromPoint(pointSaved);
        log.info("Finishing the update point flow.");
        return pointResponse;
    }

    public void removePoint(Long pointId) {
        log.info("Starting the remove point flow.");
        var point = pointRepository.findById(pointId).orElseThrow(
                () -> new CustomException(HttpStatus.NOT_FOUND, "Point not found.")
        );
        log.info("Removing point in database.");
        pointRepository.deleteById(point.getId());
        log.info("Finishing the remove point flow.");
    }
}

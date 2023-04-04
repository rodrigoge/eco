package br.com.eco.pointservice.validators;

import br.com.eco.pointservice.domains.Point;
import br.com.eco.pointservice.exceptions.CustomException;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import java.util.Map;

@Component
@Log4j2
public class PointValidator {

    public void findPointFields(Point point, Map<Object, Object> fieldsPoint) {
        log.info("Searching point fields.");
        if (fieldsPoint.isEmpty()) return;
        fieldsPoint.forEach((key, value) -> {
            var field = ReflectionUtils.findField(Point.class, (String) key);
            if (field == null)
                throw new CustomException(
                        HttpStatus.NOT_FOUND, "Field not found."
                );
            field.setAccessible(true);
            ReflectionUtils.setField(field, point, value);
        });
        log.info("Finishing searching point fields.");
    }
}

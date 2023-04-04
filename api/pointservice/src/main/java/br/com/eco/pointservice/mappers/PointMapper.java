package br.com.eco.pointservice.mappers;

import br.com.eco.pointservice.domains.Point;
import br.com.eco.pointservice.domains.PointResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface PointMapper {

    PointResponse fromPoint(Point point);
}

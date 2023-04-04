package br.com.eco.pointservice.domains;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class PointsResponse {

    private List<PointResponse> points;

    private Integer totalNumberOfRecords;
}

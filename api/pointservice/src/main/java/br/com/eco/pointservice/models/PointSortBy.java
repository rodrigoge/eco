package br.com.eco.pointservice.models;

import br.com.eco.pointservice.domains.Point;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Root;

import java.util.List;

public interface PointSortBy {
    List<Path<Point>> getPath(Root<Point> root, CriteriaBuilder criteriaBuilder);
}

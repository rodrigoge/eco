package br.com.eco.pointservice.services;

import br.com.eco.pointservice.domains.Point;
import br.com.eco.pointservice.domains.PointRepository;
import br.com.eco.pointservice.domains.PointRequest;
import br.com.eco.pointservice.domains.PointResponse;
import br.com.eco.pointservice.domains.PointsResponse;
import br.com.eco.pointservice.enums.PointOrderByEnum;
import br.com.eco.pointservice.enums.PointSortByEnum;
import br.com.eco.pointservice.mappers.PointMapper;
import br.com.eco.pointservice.mappers.PointSortEnumMapper;
import io.micrometer.common.util.StringUtils;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
public class GetPointService {

    @Autowired
    private PointRepository pointRepository;

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private PointMapper pointMapper;

    public PointsResponse getPoints(PointRequest pointRequest) {
        log.info("Starting the get points flow.");
        var criteriaBuilder = entityManager.getCriteriaBuilder();
        var query = buildGetPointsQuery(pointRequest, criteriaBuilder);
        log.info("Executing queries and mapping the response");
        var points = query.getResultList();
        var response = buildPoints(points);
        var countQuery = buildPointCountQuery(pointRequest, criteriaBuilder);
        var count = entityManager.createQuery(countQuery).getSingleResult().intValue();
        var pointsResponse = PointsResponse
                .builder()
                .points(response)
                .totalNumberOfRecords(count)
                .build();
        log.info("Finishing the get points flow.");
        return pointsResponse;
    }

    public TypedQuery<Point> buildGetPointsQuery(PointRequest pointRequest, CriteriaBuilder criteriaBuilder) {
        log.info("Initializing query for get points.");
        var query = criteriaBuilder.createQuery(Point.class);
        var root = query.from(Point.class);
        var predicates = buildPredicates(pointRequest, criteriaBuilder, root);
        query.select(root);
        query.where(predicates.toArray(new Predicate[0]));
        sortingAndOrderQuery(
                query,
                criteriaBuilder,
                root,
                PointSortByEnum.valueOf(pointRequest.getSortByEnum().toUpperCase()),
                PointOrderByEnum.valueOf(pointRequest.getOrderByEnum().toUpperCase())
        );
        var typedQuery = entityManager.createQuery(query);
        paginationQuery(typedQuery, pointRequest.getOffset(), pointRequest.getLimit());
        log.info("Finishing query for get points.");
        return typedQuery;
    }

    private List<Predicate> buildPredicates(PointRequest point,
                                            CriteriaBuilder criteriaBuilder,
                                            Root<Point> root) {
        log.info("Building predicates for request.");
        var predicates = new ArrayList<Predicate>();
        if (!StringUtils.isEmpty(point.getName())) {
            predicates.add(criteriaBuilder.like(
                    criteriaBuilder.lower(root.get("name")), "%" + point.getName() + "%")
            );
        }
        if (!StringUtils.isEmpty(point.getUf())) {
            predicates.add(criteriaBuilder.like(
                    criteriaBuilder.lower(root.get("uf")), "%" + point.getUf() + "%")
            );
        }
        if (!StringUtils.isEmpty(point.getCity())) {
            predicates.add(criteriaBuilder.like(
                    criteriaBuilder.lower(root.get("city")), "%" + point.getCity() + "%")
            );
        }
        if (!StringUtils.isEmpty(point.getAddress())) {
            predicates.add(criteriaBuilder.like(
                    criteriaBuilder.lower(root.get("address")), "%" + point.getAddress() + "%")
            );
        }
        if (!StringUtils.isEmpty(point.getEmail())) {
            predicates.add(criteriaBuilder.like(
                    criteriaBuilder.lower(root.get("email")), "%" + point.getEmail() + "%")
            );
        }
        log.info("Finishing predicates for request.");
        return predicates;
    }

    public List<PointResponse> buildPoints(List<Point> points) {
        log.info("Initializing mapping from points.");
        return points
                .stream()
                .map(pointMapper::fromPoint)
                .collect(Collectors.toList());
    }

    private void sortingAndOrderQuery(CriteriaQuery<Point> query,
                                      CriteriaBuilder criteriaBuilder,
                                      Root<Point> root,
                                      PointSortByEnum pointSortByEnum,
                                      PointOrderByEnum pointOrderByEnum) {
        log.info("Initializing sorting and ordering query.");
        var sortType = PointSortEnumMapper.toSort(pointSortByEnum);
        var pathList = sortType.getPath(root, criteriaBuilder);
        var orderList = pathList
                .stream()
                .map((path) -> pointOrderByEnum.getDescription().equals("desc") ?
                        criteriaBuilder.desc(path) :
                        criteriaBuilder.asc(path))
                .toList();
        query.orderBy(orderList);
        log.info("Finishing sorting and ordering query.");
    }

    private void paginationQuery(TypedQuery<Point> typedQuery,
                                 Integer offset,
                                 Integer limit) {
        log.info("Initializing pagination query.");
        if (offset != null && limit != null) typedQuery.setFirstResult(offset * limit);
        if (limit != null) typedQuery.setMaxResults(limit);
    }

    public CriteriaQuery<Long> buildPointCountQuery(PointRequest pointRequest, CriteriaBuilder criteriaBuilder) {
        log.info("Initializing counting query for request.");
        var count = criteriaBuilder.createQuery(Long.class);
        var root = count.from(Point.class);
        var predicates = buildPredicates(pointRequest, criteriaBuilder, root);
        count.select(criteriaBuilder.count(root));
        count.where(predicates.toArray(new Predicate[0]));
        log.info("Finishing counting query for request.");
        return count;
    }
}

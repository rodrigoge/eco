package br.com.eco.userservice.services;

import br.com.eco.userservice.domains.User;
import br.com.eco.userservice.domains.UserRepository;
import br.com.eco.userservice.domains.UserRequest;
import br.com.eco.userservice.domains.UserResponse;
import br.com.eco.userservice.domains.UsersResponse;
import br.com.eco.userservice.enums.UserOrderByEnum;
import br.com.eco.userservice.enums.UserSortByEnum;
import br.com.eco.userservice.mappers.UserMapper;
import br.com.eco.userservice.mappers.UserSortEnumMapper;
import br.com.eco.userservice.validators.UserValidator;
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
public class GetUserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserValidator userValidator;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private EntityManager entityManager;

    public UsersResponse getUsers(UserRequest userRequest) {
        log.info("Starting the get users flow.");
        var criteriaBuilder = entityManager.getCriteriaBuilder();
        var query = buildGetUsersQuery(userRequest, criteriaBuilder);
        log.info("Executing queries and mapping the response");
        var users = query.getResultList();
        var response = buildUsers(users);
        var countQuery = buildUserCountQuery(userRequest, criteriaBuilder);
        var count = entityManager.createQuery(countQuery).getSingleResult().intValue();
        var usersResponse = UsersResponse
                .builder()
                .users(response)
                .totalNumberOfRecords(count)
                .build();
        log.info("Finishing the get users flow.");
        return usersResponse;
    }

    public TypedQuery<User> buildGetUsersQuery(UserRequest userRequest, CriteriaBuilder criteriaBuilder) {
        log.info("Initializing query for get users.");
        var query = criteriaBuilder.createQuery(User.class);
        var root = query.from(User.class);
        var predicates = buildPredicates(userRequest, criteriaBuilder, root);
        query.select(root);
        query.where(predicates.toArray(new Predicate[0]));
        sortingAndOrderQuery(
                query,
                criteriaBuilder,
                root,
                UserSortByEnum.valueOf(userRequest.getSortByEnum().toUpperCase()),
                UserOrderByEnum.valueOf(userRequest.getOrderByEnum().toUpperCase())
        );
        var typedQuery = entityManager.createQuery(query);
        paginationQuery(typedQuery, userRequest.getOffset(), userRequest.getLimit());
        log.info("Finishing query for get users.");
        return typedQuery;
    }

    private List<Predicate> buildPredicates(UserRequest user,
                                            CriteriaBuilder criteriaBuilder,
                                            Root<User> root) {
        log.info("Building predicates for request.");
        var predicates = new ArrayList<Predicate>();
        if (!StringUtils.isEmpty(user.getName())) {
            predicates.add(criteriaBuilder.like(
                    criteriaBuilder.lower(root.get("name")), "%" + user.getName() + "%")
            );
        }
        if (!StringUtils.isEmpty(user.getEmail())) {
            predicates.add(criteriaBuilder.like(
                    criteriaBuilder.lower(root.get("email")), "%" + user.getEmail() + "%")
            );
        }
        log.info("Finishing predicates for request.");
        return predicates;
    }

    public List<UserResponse> buildUsers(List<User> users) {
        log.info("Initializing mapping from users.");
        return users
                .stream()
                .map(userMapper::fromUser)
                .collect(Collectors.toList());
    }

    private void sortingAndOrderQuery(CriteriaQuery<User> query,
                                      CriteriaBuilder criteriaBuilder,
                                      Root<User> root,
                                      UserSortByEnum userSortByEnum,
                                      UserOrderByEnum userOrderByEnum) {
        log.info("Initializing sorting and ordering query.");
        var sortType = UserSortEnumMapper.toSort(userSortByEnum);
        var pathList = sortType.getPath(root, criteriaBuilder);
        var orderList = pathList
                .stream()
                .map((path) -> userOrderByEnum.getDescription().equals("desc") ?
                        criteriaBuilder.desc(path) :
                        criteriaBuilder.asc(path))
                .toList();
        query.orderBy(orderList);
        log.info("Finishing sorting and ordering query.");
    }

    private void paginationQuery(TypedQuery<User> typedQuery,
                                 Integer offset,
                                 Integer limit) {
        log.info("Initializing pagination query.");
        if (offset != null && limit != null) typedQuery.setFirstResult(offset * limit);
        if (limit != null) typedQuery.setMaxResults(limit);
    }

    public CriteriaQuery<Long> buildUserCountQuery(UserRequest userRequest, CriteriaBuilder criteriaBuilder) {
        log.info("Initializing counting query for request.");
        var count = criteriaBuilder.createQuery(Long.class);
        var root = count.from(User.class);
        var predicates = buildPredicates(userRequest, criteriaBuilder, root);
        count.select(criteriaBuilder.count(root));
        count.where(predicates.toArray(new Predicate[0]));
        log.info("Finishing counting query for request.");
        return count;
    }
}

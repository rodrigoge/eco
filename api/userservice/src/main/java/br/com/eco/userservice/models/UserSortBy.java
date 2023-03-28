package br.com.eco.userservice.models;

import br.com.eco.userservice.domains.User;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Root;

import java.util.List;

public interface UserSortBy {
    List<Path<User>> getPath(Root<User> root, CriteriaBuilder criteriaBuilder);
}

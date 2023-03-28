package br.com.eco.userservice.models;

import br.com.eco.userservice.domains.User;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Root;

import java.util.List;

public class UserSortByEmail implements UserSortBy {

    @Override
    public List<Path<User>> getPath(Root<User> root, CriteriaBuilder criteriaBuilder) {
        return List.of(root.join("email", JoinType.LEFT));
    }
}

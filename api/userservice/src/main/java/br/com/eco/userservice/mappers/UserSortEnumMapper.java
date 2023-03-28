package br.com.eco.userservice.mappers;

import br.com.eco.userservice.enums.UserSortByEnum;
import br.com.eco.userservice.models.UserSortBy;
import br.com.eco.userservice.models.UserSortByEmail;
import br.com.eco.userservice.models.UserSortByName;

public class UserSortEnumMapper {

    public static UserSortBy toSort(UserSortByEnum userSortByEnum) {
        return switch (userSortByEnum) {
            case NAME -> new UserSortByName();
            case EMAIL -> new UserSortByEmail();
        };
    }
}

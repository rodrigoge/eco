package br.com.eco.userservice.domains;

import br.com.eco.userservice.enums.UserOrderByEnum;
import br.com.eco.userservice.enums.UserSortByEnum;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserRequest {

    private String name;

    private String email;

    private Integer offset;

    private Integer limit;

    private UserSortByEnum sortByEnum;

    private UserOrderByEnum orderByEnum;
}

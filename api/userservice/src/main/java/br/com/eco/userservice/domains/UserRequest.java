package br.com.eco.userservice.domains;

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

    private String sortByEnum;

    private String orderByEnum;
}

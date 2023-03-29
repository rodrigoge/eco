package br.com.eco.userservice.domains;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class UsersResponse {

    private List<UserResponse> users;

    private Integer totalNumberOfRecords;
}

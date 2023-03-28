package br.com.eco.userservice.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum UserSortByEnum {

    NAME("name"),
    EMAIL("email");

    private String description;
}

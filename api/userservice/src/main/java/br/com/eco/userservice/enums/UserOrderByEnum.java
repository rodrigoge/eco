package br.com.eco.userservice.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum UserOrderByEnum {

    ASC("asc"),
    DESC("desc");

    private String description;
}

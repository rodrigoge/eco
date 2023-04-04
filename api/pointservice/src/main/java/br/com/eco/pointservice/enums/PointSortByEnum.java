package br.com.eco.pointservice.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum PointSortByEnum {

    NAME("name"),
    EMAIL("email"),
    CITY("city"),
    ADDRESS("address"),
    UF("uf");

    private String description;
}

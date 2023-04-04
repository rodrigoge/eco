package br.com.eco.pointservice.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum PointOrderByEnum {

    ASC("asc"),
    DESC("desc");

    private String description;
}

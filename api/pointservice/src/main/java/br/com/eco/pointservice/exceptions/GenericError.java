package br.com.eco.pointservice.exceptions;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class GenericError {

    private Integer status;

    private String time;

    private String message;
}

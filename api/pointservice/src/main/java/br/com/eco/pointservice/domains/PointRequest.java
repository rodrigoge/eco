package br.com.eco.pointservice.domains;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PointRequest {

    private String name;

    private String address;

    private String city;

    private String uf;

    private String email;

    private Integer offset;

    private Integer limit;

    private String sortByEnum;

    private String orderByEnum;
}

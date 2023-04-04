package br.com.eco.pointservice.domains;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PointResponse {

    private String name;

    private String address;

    private String city;

    private String uf;

    private String initialHour;

    private String endHour;

    private String email;

    private String phoneNumber;
}

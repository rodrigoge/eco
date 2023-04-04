package br.com.eco.pointservice.domains;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "points")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Point {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @NotBlank(message = "Name must not be empty.")
    @Size(max = 250, message = "This name is exceed limit.")
    private String name;

    @Column
    @NotBlank(message = "Address must not be empty.")
    @Size(max = 250, message = "This address is exceed limit.")
    private String address;

    @Column
    @NotBlank(message = "City must not be empty.")
    @Size(max = 250, message = "This city is exceed limit.")
    private String city;

    @Column
    @NotBlank(message = "UF must not be empty.")
    @Size(max = 2, message = "This UF is exceed limit.")
    private String uf;

    @Column
    @NotBlank(message = "Initial hour must not be empty.")
    @Size(max = 2, message = "This initial hour is exceed limit.")
    private String initialHour;

    @Column
    @NotBlank(message = "End hour must not be empty.")
    @Size(max = 2, message = "This end hour is exceed limit.")
    private String endHour;

    @Column
    @NotBlank(message = "E-mail must not be empty.")
    @Email(message = "This e-mail is not valid.")
    @Size(max = 250, message = "This e-mail is exceed limit.")
    private String email;

    @Column
    @NotBlank(message = "Phone must not be empty.")
    @Size(max = 14, message = "This phone is exceed limit.")
    private String phoneNumber;
}

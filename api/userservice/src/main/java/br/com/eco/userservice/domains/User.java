package br.com.eco.userservice.domains;

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
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @NotBlank(message = "Name must not be empty.")
    @Size(max = 250, message = "This name is exceed limit.")
    private String name;

    @Column
    @NotBlank(message = "E-mail must not be empty.")
    @Email(message = "This e-mail is not valid.")
    @Size(max = 250, message = "This e-mail is exceed limit.")
    private String email;

    @Column
    @NotBlank(message = "Password must not be empty.")
    @Size(max = 250, message = "This password is exceed limit.")
    private String password;
}

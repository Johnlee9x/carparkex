package com.laptrinh.carparkex.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false, length = 50)
    @Size(min = 3, max = 50)
    private String fullName;

    @NotNull
    @Column(nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Past
    private Date birthday;

    @NotNull(message = "phone is mandatory")
    @Column(nullable = false, length = 10)
    private Integer phone;

    @NotNull
    @Column(nullable = false, length = 1)
    @Min(value = 0)
    @Max(value = 1)
    private Integer sex;

    @NotEmpty
    @Column(unique = true, nullable = false, length = 50)
    @Size(max = 50)
    @Email(message = "Email is not valid", regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
    private String email;

    @NotBlank
    @Column(nullable = false)
    @Size(min = 6)
    private String password;

    @NotBlank(message = "address is mandatory")
    @Column(nullable = false, length = 50)
    @Size(min = 3, max = 50)
    private String address;

    @NotBlank(message = "account is mandatory")
    @Column(unique = true, nullable = false, length = 50)
    @Size(min = 3, max = 50)
    private String account;

    @NotBlank
    @Column(nullable = false, length = 50)
    @Size(min = 3, max = 50)
    private String department;

}

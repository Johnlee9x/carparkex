package com.laptrinh.carparkex.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeRequest {

    @NotBlank
    @Column(nullable = false, length = 50)
    @Size(min = 3, max = 50, message = "The number of characters in the field fullname cannot be less than 3 and greater than 50")
    private String fullName;

    @NotNull(message = "birthday is mandatory")
    @Column(nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Past(message = "birthday must be a date in the past")
    private Date birthday;

    @NotNull(message = "phone is mandatory")
    @Column(nullable = false, length = 10)
    private Integer phone;

    @NotNull(message = "sex is mandatory")
    @Column(nullable = false, length = 1)
    @Min(value = 0, message = "the value of the sex field can only be 0 or 1 ")
    @Max(value = 1, message = "sex cannot be greater than 1")
    private Integer sex;

    @NotEmpty(message = "Email is mandatory")
    @Column(unique = true, nullable = false, length = 50)
    @Size(max = 50)
    @Email(message = "Email is not valid", regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
    private String email;

    @NotBlank(message = "password is mandatory")
    @Column(nullable = false)
    @Size(min = 6, message = "password must have at least 6 characters")
    private String password;

    @NotBlank(message = "address is mandatory")
    @Column(nullable = false, length = 50)
    @Size(min = 3, max = 50)
    private String address;

    @NotBlank(message = "account is mandatory")
    @Column(unique = true, nullable = false, length = 50)
    @Size(min = 3, max = 50)
    private String account;

    @NotBlank(message = "department is mandatory")
    @Column(nullable = false, length = 50)
    @Size(min = 3, max = 50)
    private String department;
}

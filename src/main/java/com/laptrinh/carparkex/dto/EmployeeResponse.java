package com.laptrinh.carparkex.dto;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeResponse {
    private Long id;

    private String fullName;

    private Date birthday;

    private Integer phone;

    private String address;

    private String department;
}

package com.laptrinh.carparkex.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
public class BookingOffice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookId;

    @NotNull(message = "endContactDeadline is mandatory")
    @Column(nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endContactDeadline;

    @NotNull(message = "startContactDeadline is mandatory")
    @Column(nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startContactDeadline;

    @NotBlank(message = "officeName is mandatory")
    @Column(nullable = false, length = 50, unique = true)
    @Size(max = 50)
    private String officeName;

    @NotNull(message = "officePhone is mandatory")
    @Column(nullable = false, length = 10)
    private Integer officePhone;

    @NotBlank(message = "officePlace is mandatory")
    @Column(nullable = false, length = 50, unique = true)
    @Size(max = 50)
    private String officePlace;

    @NotNull(message = "officePrice is mandatory")
    @Column(nullable = false, length = 20)
    @Min(value = 0)
    private Double officePrice;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tripId", nullable = false)
    private Trip trip;
}

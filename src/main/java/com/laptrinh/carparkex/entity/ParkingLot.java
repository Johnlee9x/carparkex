package com.laptrinh.carparkex.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "parkId")
public class ParkingLot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long parkId;

    @NotNull
    @Column(nullable = false, length = 20)
    @Min(value = 0)
    private Integer parkArea;

    @NotBlank
    @Column(nullable = false, length = 50)
    @Size(min = 3, max = 50 )
    private String parkName;

    @NotBlank
    @Column(nullable = false, length = 11)
    @Size(min = 3, max = 11)
    private String parkPlace;

    @NotNull
    @Column(nullable = false, length = 20)
    @Min(value = 0)
    private Long parkPrice;

    @Column(length = 11)
    private String parkStatus ="";

    @JsonBackReference
    @OneToMany(mappedBy = "parkingLot", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Car> cars;
}

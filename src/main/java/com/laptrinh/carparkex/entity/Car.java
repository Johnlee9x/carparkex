package com.laptrinh.carparkex.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerator;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "licensePlate")
public class Car {
    @Id
    @Column(unique = true, length = 50)
    private String licensePlate;

    @Column(nullable = false, length = 11)
    private String carColor;

    @Column(length = 50, nullable = false)
    private String carType;

    @Column(nullable = false, length = 50)
    private String company;

    @OneToMany(mappedBy = "car", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Ticket> tickets;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parkId", nullable = false)
    private ParkingLot parkingLot;

}

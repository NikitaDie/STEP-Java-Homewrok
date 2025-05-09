package com.example.nikitadeveloper.rentdb.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "rentals")
@Entity
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fullName;
    private String contactPhone;
    private Integer desiredRooms;
    private String desiredDistrict;
    private Double desiredPrice;
    @OneToMany(mappedBy = "client")
    private List<Rental> rentals = new ArrayList<>();
}
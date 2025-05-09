package com.example.nikitadeveloper.rentdb.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"landlord", "rentals"})
@Entity
public class Apartment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Landlord landlord;
    private Integer rooms;
    private String district;
    private Double price;
    @OneToMany(mappedBy = "apartment")
    private List<Rental> rentals = new ArrayList<>();
    private transient Rental activeRental;
}
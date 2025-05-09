package com.example.nikitadeveloper.rentdb.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "apartments")
@Entity
public class Landlord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fullName;
    private String contactPhone;
    @OneToMany(mappedBy = "landlord")
    private List<Apartment> apartments = new ArrayList<>();
}

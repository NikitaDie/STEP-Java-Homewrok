package com.example.nikitadeveloper.rentdb.entity.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ClientSearchDTO {
    private String fullName;
    private String contactPhone;
    private Integer desiredRooms;
    private String desiredDistrict;
    private Double desiredPrice;
}
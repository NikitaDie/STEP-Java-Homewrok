package com.example.nikitadeveloper.rentdb.service;

import com.example.nikitadeveloper.rentdb.dao.ApartmentDAO;
import com.example.nikitadeveloper.rentdb.entity.Apartment;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApartmentService {
    private final ApartmentDAO apartmentDAO;

    public ApartmentService(ApartmentDAO apartmentDAO) {
        this.apartmentDAO = apartmentDAO;
    }

    @Transactional(readOnly = true)
    public List<Apartment> findApartments(String district, Integer rooms, Double price, String status) {
        return apartmentDAO.findByCriteria(district, rooms, price, status);
    }

    @Transactional(readOnly = true)
    public Apartment findById(Long id) {
        return apartmentDAO.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Apartment not found"));
    }

    @Transactional
    public void save(Apartment apartment) {
        apartmentDAO.save(apartment);
    }

    @Transactional
    public void delete(Long id) {
        apartmentDAO.delete(id);
    }
}
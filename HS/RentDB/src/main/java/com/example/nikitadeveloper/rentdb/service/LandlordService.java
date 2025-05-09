package com.example.nikitadeveloper.rentdb.service;

import com.example.nikitadeveloper.rentdb.dao.LandlordDAO;
import com.example.nikitadeveloper.rentdb.entity.Landlord;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LandlordService {
    private final LandlordDAO landlordDAO;

    public LandlordService(LandlordDAO landlordDAO) {
        this.landlordDAO = landlordDAO;
    }

    @Transactional(readOnly = true)
    public List<Landlord> findAll() {
        return landlordDAO.findAll();
    }

    @Transactional(readOnly = true)
    public Landlord findById(Long id) {
        return landlordDAO.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Landlord not found"));
    }

    @Transactional
    public void save(Landlord landlord) {
        landlordDAO.save(landlord);
    }

    @Transactional
    public void delete(Long id) {
        landlordDAO.delete(id);
    }
}
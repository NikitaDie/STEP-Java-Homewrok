package com.example.nikitadeveloper.rentdb.service;

import com.example.nikitadeveloper.rentdb.dao.ClientDAO;
import com.example.nikitadeveloper.rentdb.entity.Client;
import com.example.nikitadeveloper.rentdb.entity.dto.ClientSearchDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ClientService {
    private final ClientDAO clientDAO;

    public ClientService(ClientDAO clientDAO) {
        this.clientDAO = clientDAO;
    }

    @Transactional
    public void save(Client client) {
        clientDAO.save(client);
    }

    @Transactional
    public void delete(Long id) {
        clientDAO.delete(id);
    }

    @Transactional(readOnly = true)
    public Client findById(Long id) {
        return clientDAO.findById(id);
    }

    @Transactional(readOnly = true)
    public List<Client> findAll() {
        return clientDAO.findAll();
    }

    @Transactional(readOnly = true)
    public List<Client> search(ClientSearchDTO searchDTO) {
        return clientDAO.search(searchDTO);
    }

    @Transactional(readOnly = true)
    public List<Client> findByRecentRentals() {
        return clientDAO.findByRecentRentals();
    }

    @Transactional(readOnly = true)
    public List<Client> findByUpcomingEndRentals() {
        return clientDAO.findByUpcomingEndRentals();
    }

    @Transactional(readOnly = true)
    public List<Client> findByShortAverageRental() {
        return clientDAO.findByShortAverageRental();
    }

    @Transactional(readOnly = true)
    public List<Client> findByLongAverageRental() {
        return clientDAO.findByLongAverageRental();
    }
}
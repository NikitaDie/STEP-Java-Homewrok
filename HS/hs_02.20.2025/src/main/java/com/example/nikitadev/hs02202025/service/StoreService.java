package com.example.nikitadev.hs02202025.service;

import com.example.nikitadev.hs02202025.model.Store;
import com.example.nikitadev.hs02202025.repository.StoreRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class StoreService implements IStoreService {

    private final StoreRepository storeRepository;

    public StoreService(StoreRepository storeRepository) {
        this.storeRepository = storeRepository;
    }

    @Override
    public Iterable<Store> getAllStores() {
        return storeRepository.findAll();
    }

    @Override
    public Iterable<Store> searchStores(String query) {
        if (query == null || query.trim().isEmpty()) {
            return getAllStores();
        }
        return storeRepository.searchStores(query);
    }

    @Override
    public Optional<Store> getStoreById(UUID id) {
        return storeRepository.findById(id);
    }

    @Override
    public Store saveStore(Store store) {
        return storeRepository.save(store);
    }

    @Override
    public void deleteStore(UUID id) {
        storeRepository.deleteById(id);
    }
}

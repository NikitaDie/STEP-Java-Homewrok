package com.example.nikitadev.hs02202025.service;

import com.example.nikitadev.hs02202025.model.Store;

import java.util.Optional;
import java.util.UUID;

public interface IStoreService {
    Iterable<Store> getAllStores();

    Iterable<Store> searchStores(String query);

    Optional<Store> getStoreById(UUID id);

    Store saveStore(Store store);

    void deleteStore(UUID id);
}

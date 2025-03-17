package com.example.nikitadev.hs02202025.repository;

import com.example.nikitadev.hs02202025.model.Store;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface StoreRepository extends CrudRepository<Store, UUID> {

    @Query("""
		SELECT * FROM stores 
		WHERE LOWER(name) LIKE '%' || LOWER(:search) || '%'
			OR LOWER(address) LIKE '%' || LOWER(:search) || '%'
			OR LOWER(category::text) LIKE '%' || LOWER(:search) || '%'
	""")
    List<Store> searchStores(@Param("search") String search);
}

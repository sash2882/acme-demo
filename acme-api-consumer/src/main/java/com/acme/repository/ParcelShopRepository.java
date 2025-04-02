package com.acme.repository;

import com.acme.model.ParcelShop;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParcelShopRepository extends MongoRepository<ParcelShop, String> {
}

package com.example.cms.model.repository;

import com.example.cms.model.entity.FoodTruckOwner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodTruckOwnerRepository extends JpaRepository<FoodTruckOwner, Long> {
    FoodTruckOwner findByEmailAndPassword(String email, String password);

}
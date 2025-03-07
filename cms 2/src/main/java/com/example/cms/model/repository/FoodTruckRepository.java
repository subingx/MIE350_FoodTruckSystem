package com.example.cms.model.repository;

import com.example.cms.model.entity.FoodTruck;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodTruckRepository extends JpaRepository<FoodTruck, String> {
}
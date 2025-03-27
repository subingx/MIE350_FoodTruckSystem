package com.example.cms.model.repository;

import com.example.cms.model.entity.FoodTruck;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FoodTruckRepository extends JpaRepository<FoodTruck, String> {
    @Query(value = "SELECT f FROM FoodTruck f WHERE f.owner.id = :ownerId")
    List<FoodTruck> findFoodTruckBy_OwnerId(@Param("ownerId") Long ownerId);

}
package com.example.cms.model.repository;

import com.example.cms.model.entity.Favorite;
import com.example.cms.model.entity.FoodTruck;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface FavoriteRepository extends JpaRepository<Favorite, Long> {
    List<Favorite> findByCustomerId(Long customerId);

    @Query(value = "SELECT f.foodTruck FROM Favorite f GROUP BY f.foodTruck ORDER BY COUNT(f) DESC")
    List<FoodTruck> findTopFavoriteFoodTrucks();

    @Modifying
    @Transactional
    void deleteByCustomerId(Long customerId);

    @Modifying
    @Transactional
    void deleteByCustomerIdAndFoodTruckCode(Long customerId, String truckCode);

    @Modifying
    @Transactional
    void deleteByFoodTruckCode(String truckCode);

    List<Favorite> findByCustomerIdAndFoodTruckCode(Long customerId, String truckCode);

}

package com.example.cms.model.repository;

import com.example.cms.model.entity.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Repository
public interface MenuItemRepository extends JpaRepository<MenuItem, String> {
    @Modifying
    @Transactional
    void deleteByFoodTruckCode(String truckCode);

    @Query(value = "SELECT * FROM menuItems m INNER JOIN foodtrucks f ON m.truckCode = f.code WHERE f.code =:truckCode", nativeQuery = true)
    List<MenuItem> findByTruckCode(@Param("truckCode") String truckCode);
}

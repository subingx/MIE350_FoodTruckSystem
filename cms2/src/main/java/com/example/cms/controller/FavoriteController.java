package com.example.cms.controller;

import com.example.cms.controller.exceptions.CustomerNotFoundException;
import com.example.cms.controller.exceptions.FoodTruckNotFoundException;
import com.example.cms.model.entity.Customer;
import com.example.cms.model.entity.FoodTruck;
import com.example.cms.model.entity.Favorite;
import com.example.cms.model.repository.CustomerRepository;
import com.example.cms.model.repository.FoodTruckRepository;
import com.example.cms.model.repository.FavoriteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class FavoriteController {

    @Autowired
    private final FavoriteRepository favoriteRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private FoodTruckRepository truckRepository;

    public FavoriteController(FavoriteRepository repository) {
        this.favoriteRepository = repository;
    }

    // Add a new favorite
    @PostMapping("/favorites/{customerId}/{truckCode}")
    Favorite createFavorite(@PathVariable Long customerId, @PathVariable String truckCode) {
        Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new CustomerNotFoundException(customerId) );
        FoodTruck truck = truckRepository.findById(truckCode).orElseThrow(() -> new FoodTruckNotFoundException(truckCode));

        Favorite favorite = new Favorite();
        favorite.setCustomer(customer);
        favorite.setFoodTruck(truck);

        return favoriteRepository.save(favorite);
    }

    // Get all favorites
    @GetMapping("/favorites")
    List<Favorite> getAllFavorites() {
        return favoriteRepository.findAll();
    }

    // Get favorites by customer
    @GetMapping("/favorites/customer/{customerId}")
    List<Favorite> getFavoritesByCustomer(@PathVariable Long customerId) {
        return favoriteRepository.findByCustomerId(customerId);
    }

    // Delete a favorite
    @DeleteMapping("/favorites/{customerId}/{truckCode}")
    void deleteFavoriteByCustomerAndTruck(@PathVariable("customerId") Long customerId,
                                          @PathVariable("truckCode") String truckCode) {
        favoriteRepository.deleteByCustomerIdAndFoodTruckCode(customerId, truckCode);
    }

//    @PutMapping("/favorites/{id}")
//    Favorite updateFavorite(@PathVariable Long id, @RequestParam Long customerId, @RequestParam String truckCode) {
//        return favoriteRepository.findById(id)
//                .map(favorite -> {
//                    Customer customer = customerRepository.findById(customerId)
//                            .orElseThrow(() -> new CustomerNotFoundException(customerId));
//                    FoodTruck truck = truckRepository.findById(truckCode)
//                            .orElseThrow(() -> new FoodTruckNotFoundException(truckCode));
//
//                    favorite.setCustomer(customer);
//                    favorite.setFoodTruck(truck);
//
//                    return favoriteRepository.save(favorite);
//                })
//                .orElseThrow(() -> new RuntimeException("Favorite not found with id " + id));
//    }


    @GetMapping("/favorites/top")
    List<FoodTruck> getTopFavoriteFoodTrucks() {
        return favoriteRepository.findTopFavoriteFoodTrucks();
    }


}

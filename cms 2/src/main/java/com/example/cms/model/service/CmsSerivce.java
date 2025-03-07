package com.example.cms.model.service;

import com.example.cms.model.repository.FoodTruckOwnerRepository;
import org.springframework.stereotype.Service;

@Service
public class CmsSerivce {

    public CmsSerivce(FoodTruckOwnerRepository ownerRepository) {
    }
}

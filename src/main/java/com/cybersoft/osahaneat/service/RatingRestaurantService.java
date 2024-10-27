package com.cybersoft.osahaneat.service;

import com.cybersoft.osahaneat.entity.RatingRestaurant;
import com.cybersoft.osahaneat.repository.RatingRestaurantRepository;
import com.cybersoft.osahaneat.service.imp.RatingRestaurantServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RatingRestaurantService implements RatingRestaurantServiceImp {
    @Autowired
    private RatingRestaurantRepository ratingRestaurantRepository;

    public boolean insertRatingRestaurant(RatingRestaurant ratingRestaurant) {
        boolean isInserted = false;
        try {
            ratingRestaurantRepository.save(ratingRestaurant);
            isInserted = true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return isInserted;
    }
}

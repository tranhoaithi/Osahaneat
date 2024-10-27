package com.cybersoft.osahaneat.controller;

import com.cybersoft.osahaneat.entity.RatingRestaurant;
import com.cybersoft.osahaneat.payload.ResponseData;
import com.cybersoft.osahaneat.service.RatingRestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rating-restaurant")
public class RatingRestaurantController {
    @Autowired
    private RatingRestaurantService ratingRestaurantService;

    @PostMapping("/insert")
    public ResponseEntity<ResponseData> insertRatingRestaurant(@RequestBody RatingRestaurant ratingRestaurant) {
        ResponseData responseData = new ResponseData();
        boolean isInserted = ratingRestaurantService.insertRatingRestaurant(ratingRestaurant);

        if (isInserted) {
            responseData.setSuccess(true);
            responseData.setDesc("Rating restaurant inserted successfully.");
            return new ResponseEntity<>(responseData, HttpStatus.CREATED);
        } else {
            responseData.setSuccess(false);
            responseData.setDesc("Failed to insert rating restaurant.");
            return new ResponseEntity<>(responseData, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

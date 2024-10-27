package com.cybersoft.osahaneat.controller;

import com.cybersoft.osahaneat.entity.MenuRestaurant;
import com.cybersoft.osahaneat.payload.ResponseData;
import com.cybersoft.osahaneat.service.imp.MenuRestaurantServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/menurestaurant")
public class MenuRestaurantController {

    @Autowired
    private MenuRestaurantServiceImp menuRestaurantServiceImp;

    @PostMapping("/create")
    public ResponseEntity<?> createMenuRestaurant(@RequestParam int cateId, @RequestParam int resId) {
        ResponseData responseData = new ResponseData();
        boolean  isInserted = menuRestaurantServiceImp.createMenuRestaurant(cateId, resId);
        if(isInserted){
            responseData.setSuccess(isInserted);
            responseData.setDesc("create menu restaurant success");
        } else {
            responseData.setSuccess(false);
            responseData.setDesc("create menu restaurant failed");
        }

        return new ResponseEntity<>(responseData, HttpStatus.CREATED);
    }
}

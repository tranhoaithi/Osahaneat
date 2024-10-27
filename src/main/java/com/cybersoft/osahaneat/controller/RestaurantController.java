package com.cybersoft.osahaneat.controller;


import com.cybersoft.osahaneat.payload.ResponseData;
import com.cybersoft.osahaneat.service.imp.RestaurantServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin(origins = "http://127.0.0.1:5500/")
@RestController
@RequestMapping("/restaurant")
public class RestaurantController {
    @Autowired
    RestaurantServiceImp restaurantServiceImp;

    @CrossOrigin(origins = "http://127.0.0.1:5500/")
    @GetMapping("/getallrestaurant")
    public ResponseEntity<?> getAllRestaurant() {
        ResponseData responseData = new ResponseData();
        responseData.setData( restaurantServiceImp.getAllRestaurants());
        return  new ResponseEntity<>(responseData, HttpStatus.OK);
    }

    // file upload thì nhớ dùng Post ssMethod
    @PostMapping("/create")
    public ResponseEntity<?> createRestaurant(
            @RequestParam MultipartFile image,
            @RequestParam String title,
            @RequestParam String subtitle,
            @RequestParam String desc,
            @RequestParam boolean isFreeship,
            @RequestParam String address,
            @RequestParam String openDate
    ) {
        ResponseData responseData = new ResponseData();
        boolean isInserted = restaurantServiceImp.insertRestaurant(image, title, subtitle, desc, isFreeship, address, openDate);
        responseData.setData(isInserted);
        return new ResponseEntity<>(responseData, HttpStatus.CREATED);
    }

    @GetMapping("/getrestaurantbyid")
    public ResponseEntity<?> getRestaurantById(@RequestParam int id) {
        ResponseData responseData = new ResponseData();
        responseData.setData(restaurantServiceImp.getRestaurantById(id));
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }
}

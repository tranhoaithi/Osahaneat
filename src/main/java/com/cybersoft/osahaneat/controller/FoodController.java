package com.cybersoft.osahaneat.controller;

import com.cybersoft.osahaneat.dto.FoodDTO;
import com.cybersoft.osahaneat.payload.ResponseData;
import com.cybersoft.osahaneat.service.imp.FoodServiceImp;
import jakarta.persistence.Column;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/food")
public class FoodController {
    @Autowired
    FoodServiceImp foodServiceImp;

    @PostMapping("/create")
    public ResponseEntity<?> createFood(
            // những thuộc tính ở đây được căn cứ ở database
            // xem database cần lưu gì thì mình sẽ cung cấp cái đấy
            @RequestParam MultipartFile image,
            @RequestParam String title,
            @RequestParam String timeShip,
            @RequestParam double price,
            @RequestParam boolean isFreeship,
            @RequestParam int cate_id
    ) {
        ResponseData responseData = new ResponseData();
        boolean isInserted = foodServiceImp.createFood(image, title, timeShip,price ,isFreeship, cate_id);
        if(isInserted){
            responseData.setDesc("create food successfully");
            responseData.setSuccess(Boolean.TRUE);
        }else {
            responseData.setDesc("create food failed");
            responseData.setSuccess(Boolean.FALSE);
        }

        return new ResponseEntity<>(responseData, HttpStatus.CREATED);
    }

    @GetMapping("/getallfood")
    public ResponseEntity<?> getAllFood() {
        ResponseData responseData = new ResponseData();
        List<FoodDTO> foodDTOList = foodServiceImp.getAllFood();
        responseData.setData(foodDTOList);
        responseData.setSuccess(Boolean.TRUE);
        responseData.setDesc("get food list successfully");
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }
}

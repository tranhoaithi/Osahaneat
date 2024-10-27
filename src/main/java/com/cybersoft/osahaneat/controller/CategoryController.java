package com.cybersoft.osahaneat.controller;

import com.cybersoft.osahaneat.entity.Category;
import com.cybersoft.osahaneat.payload.ResponseData;
import com.cybersoft.osahaneat.service.imp.CategoryServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryServiceImp categoryServiceImp;

    @GetMapping("/getallcategory")
    public ResponseEntity<?> getAllCategory() {
        ResponseData responseData = new ResponseData();
        responseData.setData( categoryServiceImp.getAllCategories());
        return  new ResponseEntity<>(responseData, HttpStatus.OK);
    }


    @PostMapping("/add")
    public ResponseEntity<?> addCategory(@RequestParam String categoryName) {
        ResponseData responseData = new ResponseData();
        if(categoryServiceImp.addCategory(categoryName)) {
            responseData.setDesc("add category success");
            responseData.setSuccess(true);
        }else {
            responseData.setDesc("add category fail");
            responseData.setSuccess(false);
        }

        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }
}

package com.cybersoft.osahaneat.controller;

import com.cybersoft.osahaneat.dto.UserDTO;
import com.cybersoft.osahaneat.payload.ResponseData;
import com.cybersoft.osahaneat.service.imp.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserServiceImp userServiceImp;
    @GetMapping("/getalluser")
    public ResponseEntity<?> getAllUser(){

        ResponseData responseData = new ResponseData();
        responseData.setData(userServiceImp.getAllUser());

        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }
    @PutMapping("/update")
    public ResponseEntity<?> updateUserByUsername(@RequestBody UserDTO userDTO){
        ResponseData responseData = new ResponseData();
        boolean isUpdate = userServiceImp.UpdateUser(userDTO);
        responseData.setSuccess(isUpdate);
        responseData.setDesc("updated");
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUserById(@PathVariable int id){
        ResponseData responseData = new ResponseData();
        boolean isDeleted = userServiceImp.DeleteUserById(id);
        if (isDeleted) {
            responseData.setSuccess(true);
            responseData.setDesc("User deleted successfully.");
            return new ResponseEntity<>(responseData, HttpStatus.OK);
        } else {
            responseData.setSuccess(false);
            responseData.setDesc("User not found or deletion failed.");
            return new ResponseEntity<>(responseData, HttpStatus.NOT_FOUND);
        }
    }
}

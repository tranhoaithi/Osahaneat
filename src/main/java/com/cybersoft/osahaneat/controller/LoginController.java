package com.cybersoft.osahaneat.controller;

import com.cybersoft.osahaneat.dto.UserDTO;
import com.cybersoft.osahaneat.entity.Users;
import com.cybersoft.osahaneat.payload.ResponseData;
import com.cybersoft.osahaneat.payload.UserRequest;
import com.cybersoft.osahaneat.repository.UserRepository;
import com.cybersoft.osahaneat.service.imp.LoginServiceImp;
import com.cybersoft.osahaneat.utils.JwtUtil;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Encoders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.crypto.SecretKey;
@CrossOrigin("*")
@RestController
@RequestMapping("/login")
public class LoginController {
    @Autowired
    LoginServiceImp loginServiceImp;

    @Autowired
    JwtUtil jwtUtil;

    @PostMapping("/signin")
    public ResponseEntity<?> login(@RequestParam String username, @RequestParam String password) {

        ResponseData responseData = new ResponseData();
        if(loginServiceImp.checkLogin(username, password)){
            String token = jwtUtil.generateToken(username);
            responseData.setData(token);
            responseData.setDesc("success");
            responseData.setSuccess(true);
        }else{
            responseData.setData("");
            responseData.setSuccess(false);
        }

        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody UserDTO userDTO){
        ResponseData responseData = new ResponseData();
        responseData.setData( loginServiceImp.isSignup(userDTO));
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }



}

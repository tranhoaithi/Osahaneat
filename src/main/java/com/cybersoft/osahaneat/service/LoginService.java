package com.cybersoft.osahaneat.service;

import com.cybersoft.osahaneat.dto.UserDTO;
import com.cybersoft.osahaneat.entity.Users;
import com.cybersoft.osahaneat.repository.UserRepository;
import com.cybersoft.osahaneat.service.imp.LoginServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LoginService implements LoginServiceImp {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;


    @Override
    public boolean checkLogin(String username, String password) {
        Users user = userRepository.findByUsername(username);
        return passwordEncoder.matches(password,user.getPassword());
    }

    @Override
    public boolean isSignup(UserDTO userDTO) {
        try {
            Users user = new Users();
            user.setUsername(userDTO.getUsername());
            user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
            user.setFullname(userDTO.getFullname());
            userRepository.save(user);
            return true;
        }catch (Exception e){
            System.out.println("Loi signup" + e.getMessage());
            return false;
        }

    }
}

package com.cybersoft.osahaneat.service;

import com.cybersoft.osahaneat.dto.UserDTO;
import com.cybersoft.osahaneat.entity.Users;
import com.cybersoft.osahaneat.repository.UserRepository;
import com.cybersoft.osahaneat.service.imp.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService implements UserServiceImp {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public List<UserDTO> getAllUser(){
        List<Users> listUser = userRepository.findAll();
        List<UserDTO> userDTOList = new ArrayList<>();
        for (Users user: listUser) {
            UserDTO userDTO = new UserDTO();
            userDTO.setId(user.getId());
            userDTO.setUsername(user.getUsername());
            userDTO.setPassword(user.getPassword());
            userDTO.setFullname(user.getFullname());
            userDTOList.add(userDTO);
        }
        return userDTOList;
    }



    @Override
    public boolean UpdateUser(UserDTO userDTO) {
        boolean isUpdated = false;
        try {
            Users user = userRepository.findByUsername(userDTO.getUsername());
            user.setFullname(userDTO.getFullname());
            user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
            userRepository.save(user);
            isUpdated = true;
        }catch (Exception e){
            System.out.println("update user failure " + e.getMessage());
        }
        return isUpdated;
    }

    @Override
    public boolean DeleteUserById(int id) {
        boolean isDeleted = false;
        try {
            userRepository.deleteById(id);
            isDeleted = true;
        }catch (Exception e){
            System.out.println("delete user failure " + e.getMessage());
        }
        return isDeleted;
    }

}

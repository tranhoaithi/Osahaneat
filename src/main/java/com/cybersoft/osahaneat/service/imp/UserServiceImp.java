package com.cybersoft.osahaneat.service.imp;

import com.cybersoft.osahaneat.dto.UserDTO;
import com.cybersoft.osahaneat.entity.Users;
import org.springframework.stereotype.Service;


import java.util.List;


public interface UserServiceImp {
    List<UserDTO> getAllUser();

    boolean UpdateUser(UserDTO userDTO);
    boolean DeleteUserById(int id);


}

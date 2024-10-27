package com.cybersoft.osahaneat.service.imp;

import com.cybersoft.osahaneat.dto.UserDTO;

import java.util.List;

public interface LoginServiceImp {

    boolean checkLogin(String username, String password);

    boolean isSignup(UserDTO userDTO);
}

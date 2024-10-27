package com.cybersoft.osahaneat.service.imp;

import com.cybersoft.osahaneat.dto.RestaurantDTO;
import com.cybersoft.osahaneat.entity.Restaurant;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;


public interface RestaurantServiceImp {
    List<RestaurantDTO> getAllRestaurants();
    boolean insertRestaurant( MultipartFile image,
                               String title,
                               String subtitle,
                               String desc,
                               boolean isFreeship,
                               String address,
                               String openDate);

    RestaurantDTO getRestaurantById(int id);
}

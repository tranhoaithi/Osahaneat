package com.cybersoft.osahaneat.service.imp;

import com.cybersoft.osahaneat.dto.FoodDTO;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FoodServiceImp {
    boolean createFood(MultipartFile image, String title, String timeShip, double price, boolean isFreeship, int cate_id);
    List<FoodDTO> getAllFood();
}

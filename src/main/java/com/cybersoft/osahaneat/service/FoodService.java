package com.cybersoft.osahaneat.service;

import com.cybersoft.osahaneat.dto.FoodDTO;
import com.cybersoft.osahaneat.entity.Category;
import com.cybersoft.osahaneat.entity.Food;
import com.cybersoft.osahaneat.entity.Restaurant;
import com.cybersoft.osahaneat.repository.FoodRepository;
import com.cybersoft.osahaneat.service.imp.FileStorageServiceImp;
import com.cybersoft.osahaneat.service.imp.FoodServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class FoodService implements FoodServiceImp {

    @Autowired
    FileStorageServiceImp fileStorageServiceImp;

    @Autowired
    FoodRepository foodRepository;

    @Override
    public boolean createFood(MultipartFile image, String title, String timeShip, double price, boolean isFreeship, int cate_id) {
        boolean isInserted = false;
        try {
            boolean isSaveFileSucess = fileStorageServiceImp.storeFile(image);
            if (isSaveFileSucess) {
                Food newFood = new Food();

                newFood.setImage(image.getOriginalFilename());
                newFood.setTitle(title);
                newFood.setTimeShip(timeShip);
                newFood.setPrice(price);
                newFood.setFreeship(isFreeship);

                Category category = new Category();
                category.setId(cate_id);
                newFood.setCategory(category);
                foodRepository.save(newFood);

                isInserted = true;
            }
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
        return isInserted;
    }

    @Override
    public List<FoodDTO> getAllFood() {
        List<Food> foodList = foodRepository.findAll();
        List<FoodDTO> foodDTOList = new ArrayList<>();
        for (Food food : foodList) {
            FoodDTO foodDTO = new FoodDTO();
            foodDTO.setImage(food.getImage());
            foodDTO.setTitle(food.getTitle());
            foodDTO.setTimeShip(food.getTimeShip());
            foodDTO.setPrice(food.getPrice());
            foodDTO.setFreeship(food.isFreeship());
            foodDTOList.add(foodDTO);
        }
        return foodDTOList;
    }
}

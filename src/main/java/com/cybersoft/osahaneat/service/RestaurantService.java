package com.cybersoft.osahaneat.service;

import com.cybersoft.osahaneat.dto.CategoryDTO;
import com.cybersoft.osahaneat.dto.FoodDTO;
import com.cybersoft.osahaneat.dto.RestaurantDTO;
import com.cybersoft.osahaneat.entity.*;
import com.cybersoft.osahaneat.repository.RestaurantRepository;
import com.cybersoft.osahaneat.service.imp.FileStorageServiceImp;
import com.cybersoft.osahaneat.service.imp.RestaurantServiceImp;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class RestaurantService implements RestaurantServiceImp {

    @Autowired
    RestaurantRepository restaurantRepository;

    Logger logger = org.slf4j.LoggerFactory.getLogger(RestaurantService.class);

    @Autowired
    FileStorageServiceImp fileStorageService;
    @Override
    public List<RestaurantDTO> getAllRestaurants() {
        PageRequest pageRequest = PageRequest.of(0, 10);
        Page<Restaurant> restaurants = restaurantRepository.findAll(pageRequest);


        List<RestaurantDTO> restaurantDTOs = new ArrayList<>();
        for (Restaurant restaurant : restaurants) {
            RestaurantDTO restaurantDTO = new RestaurantDTO();
            restaurantDTO.setId(restaurant.getId());
            restaurantDTO.setAddress(restaurant.getAddress());
            restaurantDTO.setFreeship(restaurant.isFreeship());
            restaurantDTO.setImage(restaurant.getImage());
            restaurantDTO.setTitle(restaurant.getTitle());
            restaurantDTO.setSubtitle(restaurant.getSubtitle());
            restaurantDTO.setRating(calculateRating(restaurant.getListRatingRestaurant()));
            restaurantDTOs.add(restaurantDTO);

        }
        // Sắp xếp danh sách RestaurantDTO theo rating giảm dần
        restaurantDTOs.sort(Comparator.comparing(RestaurantDTO::getRating).reversed());

        return restaurantDTOs;
    }

    private Double calculateRating(Set<RatingRestaurant> ratingRestaurants) {
        double sumPoint=0;
        for (RatingRestaurant ratingRestaurant : ratingRestaurants) {
            sumPoint += ratingRestaurant.getRatePoint();
        }

        double averageRating = sumPoint / ratingRestaurants.size();
        return averageRating;
    }

    @Override
    public boolean insertRestaurant(MultipartFile image, String title, String subtitle, String desc, boolean isFreeship, String address, String openDate){
        boolean isInserted = false;
        try {
            boolean isSaveFileSucess = fileStorageService.storeFile(image);
            if (isSaveFileSucess) {
                Restaurant restaurant = new Restaurant();
                restaurant.setTitle(title);
                restaurant.setSubtitle(subtitle);
                restaurant.setDesc(desc);
                restaurant.setFreeship(isFreeship);
                restaurant.setAddress(address);
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                Date date = dateFormat.parse(openDate);
                restaurant.setOpenDate(date);
                restaurant.setImage(image.getOriginalFilename());
                restaurantRepository.save(restaurant);
                isInserted = true;
            }
        }catch (Exception e) {
                throw new RuntimeException(e);
        }
        return isInserted;
    }

    @Override
    public RestaurantDTO getRestaurantById(int id) {
        Restaurant restaurant = restaurantRepository.findById(id);
        RestaurantDTO restaurantDTO = new RestaurantDTO();
        restaurantDTO.setAddress(restaurant.getAddress());
        restaurantDTO.setFreeship(restaurant.isFreeship());
        restaurantDTO.setImage(restaurant.getImage());
        restaurantDTO.setTitle(restaurant.getTitle());
        restaurantDTO.setSubtitle(restaurant.getSubtitle());
        restaurantDTO.setRating(calculateRating(restaurant.getListRatingRestaurant()));
        restaurantDTO.setOpenDate(restaurant.getOpenDate());
        restaurantDTO.setDesc(restaurant.getDesc());

        List<CategoryDTO> categoryDTOList = new ArrayList<>();
        for (MenuRestaurant menuRestaurant : restaurant.getListMenuRestaurant()) {
            Category category = menuRestaurant.getCategory();
            CategoryDTO categoryDTO = new CategoryDTO();
            categoryDTO.setCategoryName(category.getNameCate());
            List<FoodDTO> foodDTOList = new ArrayList<>();
            for (Food food : category.getListFood()){
                FoodDTO foodDTO = new FoodDTO();
                foodDTO.setTitle(food.getTitle());
                foodDTO.setPrice(food.getPrice());
                foodDTO.setImage(food.getImage());
                foodDTO.setFreeship(food.isFreeship());
                foodDTO.setDescription(food.getDescription());
                foodDTOList.add(foodDTO);
            }
            categoryDTO.setFoods(foodDTOList);

            categoryDTOList.add(categoryDTO);
        }


        restaurantDTO.setCategoryDTOList(categoryDTOList);


        return restaurantDTO;
    }


}

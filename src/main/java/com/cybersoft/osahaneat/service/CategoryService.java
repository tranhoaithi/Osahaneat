package com.cybersoft.osahaneat.service;

import com.cybersoft.osahaneat.dto.CategoryDTO;
import com.cybersoft.osahaneat.dto.FoodDTO;
import com.cybersoft.osahaneat.entity.Category;
import com.cybersoft.osahaneat.entity.Food;
import com.cybersoft.osahaneat.repository.CategoryRepository;
import com.cybersoft.osahaneat.service.imp.CategoryServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CategoryService implements CategoryServiceImp {
    @Autowired
    private CategoryRepository categoryRepository;
    @Override
    public boolean addCategory(String categoryName) {
        boolean isInserted = false;
        try {
            Category category = new Category();
            category.setNameCate(categoryName);
            category.setCreateDate(new Date());
            categoryRepository.save(category);
            isInserted = true;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return isInserted;
    }

    @Override
    public List<CategoryDTO> getAllCategories() {
        PageRequest pageRequest = PageRequest.of(0, 3, Sort.by("nameCate").descending());
        Page<Category> categoryList = categoryRepository.findAll(pageRequest);
        List<CategoryDTO> categoryDTOList = new ArrayList<>();
        for (Category category : categoryList) {
            CategoryDTO categoryDTO = new CategoryDTO();
            categoryDTO.setCategoryName(category.getNameCate());
            List<FoodDTO> foodDTOs = new ArrayList<>();
            for (Food food : category.getListFood()) {
                FoodDTO foodDTO = new FoodDTO();

                foodDTO.setImage(food.getImage());
                foodDTO.setFreeship(food.isFreeship());
                foodDTO.setPrice(food.getPrice());
                foodDTO.setTitle(food.getTitle());

                foodDTOs.add(foodDTO);
            }
            categoryDTO.setFoods(foodDTOs);

            categoryDTOList.add(categoryDTO);
        }
        return categoryDTOList;
    }

}

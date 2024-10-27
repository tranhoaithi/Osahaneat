package com.cybersoft.osahaneat.dto;

import com.cybersoft.osahaneat.entity.Food;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDTO {
    private String categoryName;
    private List<FoodDTO> foods;
}

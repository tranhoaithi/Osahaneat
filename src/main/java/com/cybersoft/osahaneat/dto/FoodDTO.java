package com.cybersoft.osahaneat.dto;

import com.cybersoft.osahaneat.entity.Category;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FoodDTO {

    private String title;
    private String image;
    private String timeShip;
    private double price;
    private boolean isFreeship;


}

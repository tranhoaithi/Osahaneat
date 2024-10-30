package com.cybersoft.osahaneat.dto;

import lombok.*;

import java.util.Date;
import java.util.List;

@Data
public class RestaurantDTO {
    private int id;

    private String title;

    private String subtitle;

    private String image;

    private boolean isFreeship;

    private String address;

    private double rating;

    private Date openDate;

    private String desc;

    private List<CategoryDTO> categoryDTOList;


}

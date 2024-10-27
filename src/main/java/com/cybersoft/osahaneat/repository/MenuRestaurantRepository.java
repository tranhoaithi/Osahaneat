package com.cybersoft.osahaneat.repository;

import com.cybersoft.osahaneat.entity.MenuRestaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

@Repository
public interface MenuRestaurantRepository extends JpaRepository<MenuRestaurant, Integer> {

}

package com.cybersoft.osahaneat.repository;

import com.cybersoft.osahaneat.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {
    @Override
    List<Restaurant> findAll();
    Restaurant findById(int id);
}

package com.cybersoft.osahaneat.service;

import com.cybersoft.osahaneat.entity.Orders;
import com.cybersoft.osahaneat.entity.Restaurant;
import com.cybersoft.osahaneat.entity.Users;
import com.cybersoft.osahaneat.repository.OrderRepository;
import com.cybersoft.osahaneat.repository.RestaurantRepository;
import com.cybersoft.osahaneat.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    public boolean insertOrder(int userId, int resId) {
        try {
            Orders order = new Orders();

            Users user = userRepository.findByID(userId);
            if (user == null) {
                return false;
            }else {
                order.setUsers(user);
            }

            Restaurant restaurant = restaurantRepository.findById(resId);
            if (restaurant == null) {
                return false;
            }else {
                order.setRestaurant(restaurant);
            }

            order.setCreateDate(new Date());
            orderRepository.save(order);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}

package com.cybersoft.osahaneat.repository;

import com.cybersoft.osahaneat.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Orders, Integer> {

}

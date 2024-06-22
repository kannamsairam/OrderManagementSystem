package com.oms.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.oms.Entity.OrderPojo;
@Repository
public interface OrderRepo extends JpaRepository<OrderPojo, Integer> {
	
}

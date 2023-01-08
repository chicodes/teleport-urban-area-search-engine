package com.teleport.searchengine.repository;

import com.teleport.searchengine.model.UrbanArea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<UrbanArea, Long> {
    //Page<Order> findAll(Pageable pageable);
    UrbanArea findOrderById(Long id);
}

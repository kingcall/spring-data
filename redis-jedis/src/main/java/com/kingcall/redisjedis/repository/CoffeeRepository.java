package com.kingcall.redisjedis.repository;

import com.kingcall.redisjedis.model.Coffee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoffeeRepository extends JpaRepository<Coffee, Long> {
}

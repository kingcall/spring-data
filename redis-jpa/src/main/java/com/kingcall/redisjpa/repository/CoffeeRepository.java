package com.kingcall.redisjpa.repository;

import com.kingcall.redisjpa.model.Coffee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoffeeRepository extends JpaRepository<Coffee, Long> {
}

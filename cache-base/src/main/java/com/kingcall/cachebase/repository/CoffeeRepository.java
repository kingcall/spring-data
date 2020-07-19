package com.kingcall.cachebase.repository;

import com.kingcall.cachebase.model.Coffee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoffeeRepository extends JpaRepository<Coffee, Long> {
}

package com.kingcall.cacheredis.repository;

import com.kingcall.cacheredis.model.Coffee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoffeeRepository extends JpaRepository<Coffee, Long> {
}

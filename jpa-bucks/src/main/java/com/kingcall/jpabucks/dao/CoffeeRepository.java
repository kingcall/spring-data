package com.kingcall.jpabucks.dao;

import com.kingcall.jpabucks.entity.Coffee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

public interface CoffeeRepository extends CrudRepository<Coffee, Long> {
}

package com.kingcall.jpabucks.dao;

import com.kingcall.jpabucks.entity.CoffeeOrder;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

public interface CoffeeOrderRepository extends CrudRepository<CoffeeOrder, Long> {
}

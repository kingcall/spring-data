package com.kingcall.jpabuckscomplex.dao;

import com.kingcall.jpabuckscomplex.entity.CoffeeOrder;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CoffeeOrderRepository extends BaseRepository<CoffeeOrder, Long> {
    List<CoffeeOrder> findByCustomerOrderById(String customer);
    List<CoffeeOrder> findByItems_Name(String name);
}

package com.kingcall.jpabuckscomplex.dao;

import com.kingcall.jpabuckscomplex.entity.CoffeeOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CoffeeOrderRepository extends JpaRepository<CoffeeOrder, Long> {
    List<CoffeeOrder> findByCustomerOrderById(String customer);
    List<CoffeeOrder> findByItems_Name(String name);

    List<CoffeeOrder> findTop3ByOrderByUpdateTimeDescIdAsc();
}

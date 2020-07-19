package com.kingcall.jpabuckscomplex.dao;

import com.kingcall.jpabuckscomplex.entity.Coffee;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CoffeeRepository extends JpaRepository<Coffee, Long> {

}

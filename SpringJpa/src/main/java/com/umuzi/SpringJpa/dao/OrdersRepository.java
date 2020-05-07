package com.umuzi.SpringJpa.dao;
import com.umuzi.SpringJpa.model.Orders;
import org.springframework.data.repository.CrudRepository;

public interface OrdersRepository extends CrudRepository<Orders,Integer> {
}

package com.umuzi.SpringJpa.dao;
import com.umuzi.SpringJpa.model.Payments;
import org.springframework.data.repository.CrudRepository;
public interface PaymentsRepository extends CrudRepository<Payments,Integer> { }

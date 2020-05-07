package com.umuzi.SpringJpa.dao;
import com.umuzi.SpringJpa.model.Employees;
import org.springframework.data.repository.CrudRepository;

public interface EmployeesRepository extends CrudRepository<Employees,Integer> { }

package com.umuzi.SpringJpa.dao;

import com.umuzi.SpringJpa.model.Products;
import org.springframework.data.repository.CrudRepository;

public interface ProductsRepository extends CrudRepository<Products, Integer> { }

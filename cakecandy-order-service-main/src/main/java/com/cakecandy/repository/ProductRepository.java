package com.cakecandy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cakecandy.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}

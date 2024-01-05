package com.example.demo.pojo.repository;

import com.example.demo.pojo.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Entity;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}

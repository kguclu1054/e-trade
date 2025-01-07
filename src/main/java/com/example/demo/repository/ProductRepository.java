package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.CrudMethodMetadata;
import org.springframework.data.repository.CrudRepository;

import com.example.demo.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}

package com.example.security_codelearn.service;

import com.example.security_codelearn.entity.Products;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.List;


public interface ProductService {
    List<Products> getAll();

    Products findById(Long id);

    void insert(Products product);

    Products findMaxId();

    void delete(Long id);

    Products update(Products newProduct , Long id);
}

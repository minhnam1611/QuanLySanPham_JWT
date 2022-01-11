package com.example.security_codelearn.service;

import com.example.security_codelearn.dao.ProductRepository;
import com.example.security_codelearn.entity.Products;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceIplm implements ProductService{
    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Products> getAll() {
        return productRepository.findAll();
    }

    @Override
    public Products findById(Long id) {
        return productRepository.findById(id).get();
    }

    @Override
    public void insert(Products product) {
        productRepository.save(product);
    }

    @Override
    public Products findMaxId() {
        return productRepository.getByMaxId();
    }

    @Override
    public void delete(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public Products update(Products newProduct, Long id) {
        Products oldProduct  = productRepository.findById(id).get();
        if(oldProduct==null){
            productRepository.save(newProduct);
            return productRepository.getByMaxId();
        }else{
            oldProduct.setName(newProduct.getName());
            oldProduct.setPrice(newProduct.getPrice());
            oldProduct.setYear(newProduct.getYear());
            productRepository.save(oldProduct);
            return productRepository.findById(id).get();
        }
    }
}

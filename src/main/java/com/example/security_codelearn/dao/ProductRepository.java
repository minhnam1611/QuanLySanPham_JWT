package com.example.security_codelearn.dao;

import com.example.security_codelearn.entity.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

@Repository
public interface ProductRepository extends JpaRepository<Products,Long> {
    @Query("SELECT p FROM Products p WHERE p.id = (SELECT MAX(a.id) FROM Products a)")
    Products getByMaxId();
}

package com.example.security_codelearn.dao;

import com.example.security_codelearn.entity.Tokens;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TokenRepository extends JpaRepository<Tokens,Long> {
    Tokens findByToken(String token);
    @Query("SELECT t FROM Tokens t WHERE t.id = ?1")
    Optional<Tokens> findById(Long id );
}

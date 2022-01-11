package com.example.security_codelearn.service;

import com.example.security_codelearn.dao.TokenRepository;
import com.example.security_codelearn.entity.Tokens;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class TokenServiceIplm implements TokensService{
    @Autowired
    TokenRepository tokenRepository;
    @Override
    public Tokens createToken(Tokens token) {
        return tokenRepository.saveAndFlush(token);
    }

    @Override
    public Tokens findByToken(String token) {
        return tokenRepository.findByToken(token);
    }


}

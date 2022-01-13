package com.example.security_codelearn.service;

import com.example.security_codelearn.entity.Tokens;
import org.springframework.stereotype.Service;

import java.util.Date;


public interface TokensService {
    Tokens createToken(Tokens token);
    Tokens findByToken(String token);
    Long  findUserByRefreshtoken(String refreshtoken);

}

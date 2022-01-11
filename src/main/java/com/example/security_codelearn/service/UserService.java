package com.example.security_codelearn.service;

import com.example.security_codelearn.security.UserPrincipal;
import org.springframework.stereotype.Service;


public interface UserService {

    UserPrincipal findByUsername(String username);
}

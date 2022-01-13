package com.example.security_codelearn.service;

import com.example.security_codelearn.dao.UserRepository;
import com.example.security_codelearn.entity.Users;
import com.example.security_codelearn.security.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserPrincipal findByUsername(String username) {
        Users user = userRepository.findByUsername(username);
        UserPrincipal userPrincipal =new UserPrincipal();
        if(user != null){
            Set<String> authorities =new HashSet<>();
            if(null!=user.getRoles()) user.getRoles().forEach(
                    r ->{
                        authorities.add(r.getName());
                    }
            );
            userPrincipal.setId(user.getId());
            userPrincipal.setUsername(user.getUsername());
            userPrincipal.setPassword(user.getPassword());
            userPrincipal.setAuthorities(authorities);
        }
        return userPrincipal;
    }
    public String findUsernameFromId(long id){
        Users user= userRepository.findById(id).get();
        return user.getUsername();
    }
}

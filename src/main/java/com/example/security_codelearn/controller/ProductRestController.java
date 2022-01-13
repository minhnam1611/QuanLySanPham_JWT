package com.example.security_codelearn.controller;

import com.example.security_codelearn.entity.Products;
import com.example.security_codelearn.entity.Tokens;
import com.example.security_codelearn.entity.Users;
import com.example.security_codelearn.security.JwtUtil;
import com.example.security_codelearn.security.UserPrincipal;
import com.example.security_codelearn.service.ProductService;
import com.example.security_codelearn.service.TokensService;
import com.example.security_codelearn.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductRestController {

    @Autowired
    UserService userService;
    @Autowired
    JwtUtil jwtUtil;
    @Autowired
    TokensService tokenService;
    @Autowired
    ProductService productService;



    @GetMapping("/getProduct")
    @PreAuthorize("hasAnyAuthority('READ')")
    public List<Products> getAll(){
        return productService.getAll();
    }
    @GetMapping("/getProduct/{id}")
    @PreAuthorize("hasAnyAuthority('READ')")
    public Products getProductById(@PathVariable Long id){
        return productService.findById(id);
    }
    @PostMapping("/insert")
    @PreAuthorize("hasAnyAuthority('INSERT')")
    public Products insertProduct(@RequestBody Products products){
        List<Products> list = productService.getAll();
        for (Products sanpham : list) {
            if(sanpham.getName().equals(products.getName())){
                return null;
            }
        }
        productService.insert(products);
        return productService.findMaxId();
    }
    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAnyAuthority('DELETE')")
    public String deleteProduct(@PathVariable Long id){
        productService.delete(id);
        return "Done";
    }
    @PutMapping("/update/{id}")
    @PreAuthorize("hasAnyAuthority('UPDATE')")
    public Products updateProduct(@RequestBody Products newProduct, @PathVariable Long id){
        return productService.update(newProduct,id);
    }


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Users user){
        UserPrincipal userPrincipal = userService.findByUsername(user.getUsername());
        if (null == userPrincipal || !user.getPassword().equals(userPrincipal.getPassword()) ) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Tài khoản hoặc mật khẩu không chính xác");
        }
        Tokens token = new Tokens();
        token.setToken(jwtUtil.generateToken(userPrincipal));
        token.setRefreshtoken(jwtUtil.generateRfToken(userPrincipal));
        token.setTokenExpDate(jwtUtil.generateExpirationDate());
        token.setRfTokenExpDate(jwtUtil.generateExpirationDaterf());
        token.setIduser(userPrincipal.getId());
        tokenService.createToken(token);
        return ResponseEntity.ok("Access token: "+token.getToken()+"\nRefresh Token: "+token.getRefreshtoken());
    }
}

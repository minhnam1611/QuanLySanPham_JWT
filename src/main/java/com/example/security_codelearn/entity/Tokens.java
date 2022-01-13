package com.example.security_codelearn.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Tokens {
    @Id
    private Long iduser;

    @Column(length = 1000)
    private String token;
    @Column(length = 1000)
    private String refreshtoken;

    private Date tokenExpDate;

    private Date rfTokenExpDate;
}

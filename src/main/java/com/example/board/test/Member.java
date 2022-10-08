package com.example.board.test;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Member {
    private int id;
    @Column(nullable = false, length = 50)
    private String userName;

    @JsonIgnore
    @Column(nullable = false, length = 150)
    private String password;

    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @Column(nullable = false, length = 6)
    private String birthDate;

    @Column(nullable = false, length = 20)
    private String phoneNumber;
}

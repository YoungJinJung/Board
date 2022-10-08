package com.example.board.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

//ORM -> java
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "users")
//@DynamicInsert //Insert시에 Null일 필드를 제외
public class User {
    @Id//Primary Key
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;//auto_increment

    @Column(nullable = false, length = 100, unique = true)
    private String username;

    @Column(length = 100)
    private String password;

    @Column(nullable = false, length = 50)
    private String email;

    @Enumerated(EnumType.STRING)
    private UserRole role;//Enum

    @Enumerated(EnumType.STRING)
    private LoginType loginType;

    @CreationTimestamp
    private Timestamp createDate;
}

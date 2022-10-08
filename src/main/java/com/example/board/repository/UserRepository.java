package com.example.board.repository;

import com.example.board.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

//DAO
//자동으로 Bean으로 등록
@Repository //생략가능
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

}
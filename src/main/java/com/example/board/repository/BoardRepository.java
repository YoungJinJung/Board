package com.example.board.repository;

import com.example.board.model.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//DAO
//자동으로 Bean으로 등록
@Repository //생략가능
public interface BoardRepository extends JpaRepository<Board, Long> {

}
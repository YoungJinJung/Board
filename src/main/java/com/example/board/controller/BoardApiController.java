package com.example.board.controller;

import com.example.board.config.auth.PrincipalDetail;
import com.example.board.dto.ReplySaveRequestDto;
import com.example.board.dto.ResponseDto;
import com.example.board.model.Board;
import com.example.board.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
public class BoardApiController {
    @Autowired
    private BoardService boardService;

    @PostMapping("/api/board")
    public ResponseDto<Integer> save(@RequestBody Board board, @AuthenticationPrincipal PrincipalDetail principalDetail) {
        boardService.writePost(board, principalDetail.getUser());
        return new ResponseDto<>(HttpStatus.OK, 1);
    }

    @DeleteMapping("/api/board/{id}")
    public ResponseDto<Integer> deleteById(@PathVariable int id) {
        boardService.deletePost(id);
        return new ResponseDto<>(HttpStatus.OK, 1);
    }

    @PutMapping("/api/board/{id}")
    public ResponseDto<Integer> update(@PathVariable int id, @RequestBody Board board) {
        boardService.updatePost(id, board);
        return new ResponseDto<>(HttpStatus.OK, 1);
    }

    @PostMapping("/api/board/{boardId}/reply")
    public ResponseDto<Integer> saveReply(@RequestBody ReplySaveRequestDto reply) {
        boardService.writeReply(reply);
        return new ResponseDto<>(HttpStatus.OK, 1);
    }

    @DeleteMapping("/api/board/reply/{replyId}")
    public ResponseDto<Integer> deleteReply(@PathVariable long replyId) {
        boardService.deleteReply(replyId);
        return new ResponseDto<>(HttpStatus.OK, 1);
    }
}

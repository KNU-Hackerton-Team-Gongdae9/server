package com.knu.community.board.controller;

import com.knu.community.board.domain.Board;
import com.knu.community.board.dto.BoardForm;
import com.knu.community.board.service.BoardService;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/board")
public class BoardController {

    private final BoardService boardService;

    @PostMapping("/write")
    public Board writeBoard(@RequestBody BoardForm boardForm, HttpServletRequest req){
        Long userId = (Long)req.getSession().getAttribute("userId");
        return boardService.writeBoard(userId,boardForm);
    }

    @GetMapping("/{boardId}")
    public Board findBoard(@PathVariable("boardId") Long boardId){
        return boardService.findById(boardId);
    }

}

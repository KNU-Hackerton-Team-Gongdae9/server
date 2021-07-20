package com.knu.community.board.controller;

import com.knu.community.board.domain.Board;
import com.knu.community.board.domain.Category;
import com.knu.community.board.dto.BoardForm;
import com.knu.community.board.service.BoardService;
import com.knu.community.email.service.AuthService;
import com.knu.community.member.repository.MemberRepository;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/board")
public class BoardController {

    private final BoardService boardService;

    private final AuthService authService;

    private final MemberRepository memberRepository;

    @PostMapping("/write")
    public Board writeBoard(@RequestBody BoardForm boardForm, HttpServletRequest req){
        String email = authService.getEmailFromJWT(req);
        Long userId = memberRepository.findByEmail(email).getId();
        return boardService.writeBoard(userId, boardForm);
    }

    @GetMapping("/{boardId}")
    public Board findOneBoard(@PathVariable("boardId") Long boardId){
        return boardService.findById(boardId);
    }

    @GetMapping("/findAuthor")
    public List<Board> findBoardByTitle(@RequestParam("title") String title){
        return boardService.findByTitle(title);
    }

    @GetMapping("/findCategory")
    public List<Board> findBoardByCategory(@RequestParam("category") Category category){
        return boardService.findByCategory(category);
    }

    @GetMapping("/findAuthor")
    public List<Board> findBoardByAuthor(@RequestParam("author") String author){
        return boardService.findByAuthor(author);
    }



}

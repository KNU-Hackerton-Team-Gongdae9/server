package com.knu.community.board.controller;

import static com.knu.community.util.ApiUtils.success;

import com.knu.community.board.domain.Board;
import com.knu.community.board.domain.Category;
import com.knu.community.board.dto.BoardDto;
import com.knu.community.board.dto.BoardForm;
import com.knu.community.board.service.BoardService;
import com.knu.community.email.service.AuthService;
import com.knu.community.error.NotFoundException;
import com.knu.community.member.repository.MemberRepository;
import com.knu.community.util.ApiUtils.ApiResult;
import java.util.List;
import java.util.stream.Collectors;
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
    public ApiResult<Board> writeBoard(@RequestBody BoardForm boardForm, HttpServletRequest req){
        String email = authService.getEmailFromJWT(req);
        Long userId = memberRepository.findByEmail(email).getId();
        try{
            return success(boardService.writeBoard(userId,boardForm));
        }
        catch(NotFoundException e){
            throw new NotFoundException(e.getMessage());
        }
    }

    @GetMapping("/{boardId}")
    public ApiResult<Board> findOneBoard(@PathVariable("boardId") Long boardId){
       try {
           return success(boardService.findById(boardId));
       }
       catch(NotFoundException e){
           throw new NotFoundException(e.getMessage());
       }
    }

    @GetMapping("/findTitle")
    public ApiResult<List<BoardDto>> findBoardByTitle(@RequestParam("title") String title){
        try{
            return success(boardService.findByTitle(title).stream().
                map(BoardDto::new)
            .collect(Collectors.toList()));
        }
        catch(Exception e){
            throw new NotFoundException(e.getMessage());
        }
    }

    @GetMapping("/findCategory")
    public ApiResult<List<BoardDto>> findBoardByCategory(@RequestParam("category") Category category){
        try{
            return success(boardService.findByCategory(category).stream().
                map(BoardDto::new)
                .collect(Collectors.toList()));
        }
        catch(Exception e){
            throw new NotFoundException(e.getMessage());
        }
    }

    @GetMapping("/findAuthor")
    public ApiResult<List<BoardDto>> findBoardByAuthor(@RequestParam("author") String author){
        try{
            return success(boardService.findByAuthor(author).stream().
                map(BoardDto::new)
                .collect(Collectors.toList()));
        }
        catch(Exception e){
            throw new NotFoundException(e.getMessage());
        }
    }



}

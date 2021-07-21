package com.knu.community.board.controller;

import static com.knu.community.util.ApiUtils.success;

import com.knu.community.board.domain.Board;
import com.knu.community.board.domain.Category;
import com.knu.community.board.dto.BoardDetailDto;
import com.knu.community.board.dto.BoardDto;
import com.knu.community.board.dto.BoardForm;
import com.knu.community.board.service.BoardService;
import com.knu.community.email.service.AuthService;
import com.knu.community.error.NotFoundException;
import com.knu.community.util.ApiUtils.ApiResult;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import java.util.stream.Collectors;
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


    @PostMapping("/write")
    public ApiResult<BoardDto> writeBoard(@RequestBody BoardForm boardForm, HttpServletRequest req)
        throws NotFoundException {
        Long userId = authService.getUserIdFromJWT(req);
        return success(new BoardDto(boardService.writeBoard(userId, boardForm)));
    }

    @GetMapping("/{boardId}")
    public ApiResult<BoardDto> findOneBoard(@PathVariable("boardId") Long boardId){
        return success(new BoardDto(boardService.findById(boardId)));
    }

    @GetMapping("/findTitle")
    public ApiResult<List<BoardDto>> findBoardByTitle(@RequestParam("title") String title){
        return success(boardService.findByTitle(title).stream()
        .map(BoardDto::new).collect(Collectors.toList()));
    }

    @GetMapping("/findCategory")
    public ApiResult<List<BoardDto>> findBoardByCategory(@RequestParam("category") Category category){
        return success(boardService.findByCategory(category).stream()
            .map(BoardDto::new).collect(Collectors.toList()));
    }

    @GetMapping("/findAuthor")
    public ApiResult<List<BoardDto>>  findBoardByAuthor(@RequestParam("author") String author){
        return success(boardService.findByAuthor(author).stream()
            .map(BoardDto::new).collect(Collectors.toList()));
    }

    @GetMapping("/findContent")
    public ApiResult<List<BoardDto>>  findBoardByContent(@RequestParam("content") String content){
        return success(boardService.findByContent(content).stream()
            .map(BoardDto::new).collect(Collectors.toList()));
    }

    @ApiOperation(value = "특정 게시물 조회", notes = "게시물 내에 포함된 댓글과 답글도 모두 조회한다.")
    @GetMapping("/findBoardWithAll")
    public ApiResult<List<BoardDetailDto>> findBoardWithAll(@RequestParam("boardId") Long id){
        return success(boardService.findBoardWithAll(id));
        // TODO: 쿼리 최적화 => 계층형 구조
    }

    @ApiOperation(value = "내가 쓴 글 조회", notes = "내가 쓴 글을 모두 조회한다.")
    @GetMapping("/getAllWrites")
    public ApiResult<List<BoardDetailDto>> getMyAllWrite(HttpServletRequest req){
        Long memId = authService.getUserIdFromJWT(req);
        return success(boardService.findMyBoards(memId));
    }
}

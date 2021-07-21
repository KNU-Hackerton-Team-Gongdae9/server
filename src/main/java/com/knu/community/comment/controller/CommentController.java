package com.knu.community.comment.controller;


import static com.knu.community.util.ApiUtils.success;

import com.knu.community.board.domain.Board;
import com.knu.community.comment.domain.Comment;
import com.knu.community.comment.dto.CommentDto;
import com.knu.community.comment.dto.CommentForm;
import com.knu.community.comment.service.CommentService;
import com.knu.community.email.service.AuthService;
import com.knu.community.util.ApiUtils.ApiResult;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javassist.NotFoundException;
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
@RequestMapping("/api/comment")
public class CommentController {

    private final CommentService commentService;

    private final AuthService authService;

    @PostMapping("/write/{boardId}")
    public ApiResult<CommentDto> writeComment(@RequestBody CommentForm commentForm, @PathVariable("boardId") Long boardId,
        HttpServletRequest req) throws NotFoundException {
        Long userId = authService.getUserIdFromJWT(req);

        return success(new CommentDto(commentService.writeComment(boardId, userId, commentForm)));
    }

    @ApiOperation(value = "내가 쓴 댓글 조회", notes = "내가 쓴 댓글을 모두 조회한다.")
    @GetMapping("/getAllComments")
    public ApiResult<List<CommentDto>> getMyAllWrite(HttpServletRequest req) throws NotFoundException {
        Long memId = authService.getUserIdFromJWT(req);
        return success(commentService.findMyComments(memId).stream().map(CommentDto::new)
        .collect(Collectors.toList()));
    }
}

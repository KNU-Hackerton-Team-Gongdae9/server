package com.knu.community.comment.controller;


import com.knu.community.comment.domain.Comment;
import com.knu.community.comment.dto.CommentForm;
import com.knu.community.comment.service.CommentService;
import com.knu.community.email.service.AuthService;
import javassist.NotFoundException;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
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
    public Comment wrieComment(@RequestBody CommentForm commentForm, @PathVariable("boardId") Long boardId,
        HttpServletRequest req) throws NotFoundException {
        Long userId = authService.getUserIdFromJWT(req);

        Comment comment = commentService.writeComment(boardId, userId, commentForm);
        return comment;
    }

}

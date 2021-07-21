package com.knu.community.board.dto;

import com.knu.community.board.domain.Board;
import com.knu.community.board.domain.Category;
import com.knu.community.comment.domain.Comment;
import java.util.List;
import lombok.Data;

@Data
public class BoardDetailDto {

    private Long boardId;

    private Category category;

    private String title;

    private String content;

    private String author;

    private List<Comment> comments;

    public BoardDetailDto(Board board) {
        this.boardId = board.getId();
        this.category = board.getCategory();
        this.title = board.getTitle();
        this.content = board.getContent();
        this.author = board.getAuthor();
        this.comments = board.getCommentList();
    }
}

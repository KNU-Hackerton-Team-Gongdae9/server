package com.knu.community.comment.dto;


import com.knu.community.comment.domain.Comment;
import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class CommentDto {

    private String author;

    private String content;

    private LocalDateTime time;

    public CommentDto(Comment comment){
        this.author=comment.getAuthor();
        this.content=comment.getContent();
        this.time=comment.getLastModifiedDate();
    }
}

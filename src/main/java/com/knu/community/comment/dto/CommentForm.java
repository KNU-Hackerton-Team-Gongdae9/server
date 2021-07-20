package com.knu.community.comment.dto;



import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CommentForm {

    private String content;

    private String author;

}

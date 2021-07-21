package com.knu.community.comment.dto;



import lombok.Builder;
import lombok.Getter;

@Getter
public class CommentForm {

    private String content;

    private String author;

    public CommentForm(){}

}

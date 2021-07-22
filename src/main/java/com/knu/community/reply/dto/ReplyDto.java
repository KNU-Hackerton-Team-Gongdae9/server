package com.knu.community.reply.dto;

import com.knu.community.reply.domain.Reply;
import lombok.Getter;

@Getter
public class ReplyDto {

    private Long id;

    private String content;

    private String author;

    public ReplyDto(Reply reply){
        this.id = reply.getId();
        this.content = reply.getContent();
        this.author = reply.getAuthor();
    }
}

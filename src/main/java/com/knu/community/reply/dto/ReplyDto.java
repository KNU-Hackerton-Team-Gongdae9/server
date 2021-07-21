package com.knu.community.reply.dto;

import com.knu.community.reply.domain.Reply;
import lombok.Getter;

@Getter
public class ReplyDto {
    private String content;

    private String author;

    public ReplyDto(Reply reply){
        this.content = reply.getContent();
        this.author = reply.getAuthor();
    }
}

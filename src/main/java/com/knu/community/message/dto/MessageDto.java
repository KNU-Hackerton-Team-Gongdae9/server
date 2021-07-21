package com.knu.community.message.dto;

import com.knu.community.message.domain.Message;
import lombok.Getter;

@Getter
public class MessageDto {
    private String content;
    private Boolean read;

    public MessageDto(Message message){
        this.content = message.getContent();
        this.read = message.getRead();
    }
}

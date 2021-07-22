package com.knu.community.message.dto;

import com.knu.community.message.domain.Message;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class MessageDto {
    private String nickname;
    private String content;
    private Boolean read;
    private LocalDateTime time;

    public MessageDto(Message message){
        this.nickname = message.getSender().getNickname();
        this.content = message.getContent();
        this.read = message.getRead();
        this.time = message.getCreatedDate();
    }
}

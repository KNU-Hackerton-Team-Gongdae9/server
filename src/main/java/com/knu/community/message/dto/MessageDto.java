package com.knu.community.message.dto;

import com.knu.community.message.domain.Message;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class MessageDto {
    private String nickname;
    private String otherNickname;
    private String content;
    private String time;

    public MessageDto(Message message){
        this.nickname = message.getReceiver().getNickname();
        this.otherNickname = message.getSender().getNickname();
        this.content = message.getContent();
        this.time = message.getCreatedDate().format(DateTimeFormatter.ofPattern("yy-MM-dd HH:mm"));
    }
}

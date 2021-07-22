package com.knu.community.message.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.knu.community.base.BaseTimeEntity;
import com.knu.community.member.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class Message extends BaseTimeEntity {
    @Id
    @GeneratedValue
    @Column(name="message_id")
    private Long id;

    private String content;

    private Integer read;

    @ManyToOne
    @JoinColumn
    @JsonIgnore
    private Member sender;

    @ManyToOne
    @JoinColumn
    @JsonIgnore
    private Member receiver;

    public void setSender(Member sender) {
        if(this.sender!=null){
            this.sender.getSentMessageList().remove(this);
        }
        this.sender = sender;
        sender.getSentMessageList().add(this);
    }

    public void setReceiver(Member receiver) {
        if(this.receiver != null){
            this.receiver.getReceivedMessageList().remove(this);
        }
        this.receiver = receiver;
        receiver.getReceivedMessageList().add(this);
    }

    public static Message createInstance(Member sender, Member receiver, String content){
        Message message =  Message.builder()
                .content(content)
                .read(1)
                .build();
        message.setSender(sender);
        message.setReceiver(receiver);
        return message;
    }
}

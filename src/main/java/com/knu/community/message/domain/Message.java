package com.knu.community.message.domain;

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

    private Boolean read;

    @ManyToOne
    @JoinColumn
    private Member sender;

    @ManyToOne
    @JoinColumn
    private Member receiver;

    public static Message createInstance(Member sender, Member receiver, String content){
        return Message.builder()
                .content(content)
                .read(false)
                .sender(sender)
                .receiver(receiver).build();
    }
}

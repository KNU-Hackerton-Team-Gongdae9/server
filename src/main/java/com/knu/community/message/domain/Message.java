package com.knu.community.message.domain;


import com.knu.community.base.BaseTimeEntity;
import com.knu.community.member.domain.Member;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
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
}

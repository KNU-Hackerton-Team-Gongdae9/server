package com.knu.community.message.service;

import com.knu.community.member.domain.Member;
import com.knu.community.member.repository.MemberRepository;
import com.knu.community.message.domain.Message;
import com.knu.community.message.dto.MessageForm;
import com.knu.community.message.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SendMessageService {
    private final MemberRepository memberRepository;
    private final MessageRepository msgRepository;

    @Transactional
    public void send(Long senderId, Long receiverId, MessageForm messageForm) {
        Member sender = memberRepository.findById(senderId).orElseThrow(IllegalArgumentException::new);
        Member receiver = memberRepository.findById(receiverId).orElseThrow(IllegalArgumentException::new);

        msgRepository.save(Message.createInstance(sender, receiver, messageForm.getContent()));
    }
}

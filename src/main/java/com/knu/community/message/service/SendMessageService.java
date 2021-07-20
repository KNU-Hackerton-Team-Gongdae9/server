package com.knu.community.message.service;

import com.knu.community.error.NotFoundException;
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
        Member sender = memberRepository.findById(senderId).orElseThrow(()-> new NotFoundException("해당 유저를 찾을 수 없습니다."));
        Member receiver = memberRepository.findById(receiverId).orElseThrow(()-> new NotFoundException("해당 유저를 찾을 수 없습니다."));

        msgRepository.save(Message.createInstance(sender, receiver, messageForm.getContent()));
    }
}

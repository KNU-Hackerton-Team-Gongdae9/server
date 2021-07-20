package com.knu.community.message.service;

import com.knu.community.member.repository.MemberRepository;
import com.knu.community.message.domain.Message;
import com.knu.community.message.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SearchMessageService {
    private final MemberRepository memberRepository;
    private final MessageRepository msgRepository;

    public List<Message> searchReceived(Long userId) {
        if(!memberRepository.existsById(userId)) throw new IllegalArgumentException("해당 유저가 없습니다.");
        return msgRepository.findAllByReceiver_Id(userId);
    }

    public List<Message> searchSent(Long userId) {
        if(!memberRepository.existsById(userId)) throw new IllegalArgumentException("해당 유저가 없습니다.");
        return msgRepository.findAllBySender_Id(userId);
    }
}

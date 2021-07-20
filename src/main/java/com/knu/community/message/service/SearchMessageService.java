package com.knu.community.message.service;

import com.knu.community.error.NotFoundException;
import com.knu.community.message.domain.Message;
import com.knu.community.message.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SearchMessageService {
    private final MessageRepository msgRepository;

    public List<Message> searchReceived(Long userId) {
        return msgRepository.findAllByReceiver_Id(userId).orElseThrow(() -> new NotFoundException("메시지를 찾을 수 없습니다."));
    }

    public List<Message> searchSent(Long userId) {
        return msgRepository.findAllBySender_Id(userId).orElseThrow(() -> new NotFoundException("메시지를 찾을 수 없습니다."));
    }
}

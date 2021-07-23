package com.knu.community.message.service;

import com.knu.community.error.NotFoundException;
import com.knu.community.message.domain.Message;
import com.knu.community.message.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SearchMessageService {
    private final MessageRepository msgRepository;

    public List<Message> search(Long userId){
        return msgRepository.findAllByReceiver_IdOrSender_Id(userId, userId).orElseThrow(() -> new NotFoundException("메시지를 찾을 수 없습니다."));
    }

    public List<Message> searchReceived(Long userId) {
        return msgRepository.findAllByReceiver_Id(userId).orElseThrow(() -> new NotFoundException("메시지를 찾을 수 없습니다."));
    }

    public List<Message> searchSent(Long userId) {
        return msgRepository.findAllBySender_Id(userId).orElseThrow(() -> new NotFoundException("메시지를 찾을 수 없습니다."));
    }

    public List<Message> searchChatList(String myNickname, String otherNickname) {
        List<Message> result = new ArrayList<>();

        result.addAll(msgRepository.findAllByReceiver_NicknameAndSender_Nickname(myNickname, otherNickname).orElseThrow(() -> new NotFoundException("메시지를 찾을 수 없습니다. ")));
        result.addAll(msgRepository.findAllByReceiver_NicknameAndSender_Nickname(otherNickname, myNickname).orElseThrow(() -> new NotFoundException("메시지를 찾을 수 없습니다. ")));

        return result;
    }
}

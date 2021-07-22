package com.knu.community.message.repository;

import com.knu.community.message.domain.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface MessageRepository extends JpaRepository<Message, Long> {
    Optional<List<Message>> findAllByReceiver_Id(Long userId);
    Optional<List<Message>> findAllBySender_Id(Long userId);
    Optional<List<Message>> findAllByReceiver_IdAndSender_Nickname(Long receiverId, String senderNickname);
    Optional<List<Message>> findAllByReceiver_NicknameAndSender_Id(String receiverNickname, Long senderId);
    Optional<List<Message>> findAllByReceiver_IdOrSender_Id(Long receiverId, Long senderId);
}

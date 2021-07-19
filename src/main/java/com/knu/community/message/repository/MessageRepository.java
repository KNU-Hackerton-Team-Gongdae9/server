package com.knu.community.message.repository;

import com.knu.community.message.domain.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findAllByReceiver_Id(Long userId);
    List<Message> findAllBySender_Id(Long userId);
}

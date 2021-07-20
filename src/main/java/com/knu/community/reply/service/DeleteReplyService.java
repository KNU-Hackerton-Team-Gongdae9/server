package com.knu.community.reply.service;


import com.knu.community.reply.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DeleteReplyService {
    private final ReplyRepository replyRepository;

    @Transactional
    public void delete(Long replyId) {
        replyRepository.deleteById(replyId);
    }
}

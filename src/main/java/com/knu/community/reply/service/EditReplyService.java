package com.knu.community.reply.service;


import com.knu.community.error.NotFoundException;
import com.knu.community.reply.domain.Reply;
import com.knu.community.reply.dto.ReplyForm;
import com.knu.community.reply.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class EditReplyService {
    private final ReplyRepository replyRepository;

    @Transactional
    public void edit(Long replyId, ReplyForm replyForm) {
        Reply reply = replyRepository.findById(replyId).orElseThrow(NotFoundException::new);
        reply.edit(replyForm);
    }
}

package com.knu.community.reply.service;


import com.knu.community.comment.domain.Comment;
import com.knu.community.comment.repository.CommentRepository;
import com.knu.community.error.NotFoundException;
import com.knu.community.reply.domain.Reply;
import com.knu.community.reply.dto.ReplyDto;
import com.knu.community.reply.dto.ReplyForm;
import com.knu.community.reply.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class WriteReplyService {
    private final CommentRepository commentRepository;
    private final ReplyRepository replyRepository;

    @Transactional
    public Reply write(Long commentId, ReplyForm replyForm) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(()->new NotFoundException(("Comment를 찾을 수 없습니다")));
        return replyRepository.save(Reply.createInstance(comment, replyForm));
    }
}

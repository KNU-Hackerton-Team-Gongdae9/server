package com.knu.community.comment.service;

import com.knu.community.board.domain.Board;
import com.knu.community.board.repository.BoardRepository;
import com.knu.community.comment.domain.Comment;
import com.knu.community.comment.domain.WriteComment;
import com.knu.community.comment.dto.CommentForm;
import com.knu.community.comment.repository.CommentRepository;
import com.knu.community.comment.repository.WriteCommentRepository;
import com.knu.community.error.NotFoundException;
import com.knu.community.member.domain.Member;
import com.knu.community.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;
    private final WriteCommentRepository writeCommentRepository;

    @Transactional
    public Comment writeComment(Long boardId,Long userId, CommentForm commentForm){
        Member member = memberRepository.findById(userId).orElseThrow(()->
            new NotFoundException("Member가 존재하지 않습니다."));
        Board board = boardRepository.findById(boardId).orElseThrow(()->
            new NotFoundException("Comment를 달수있는 Board가 존재하지 않습니다."));
        Comment comment = createComment(board,commentForm);

        WriteComment writeComment=new WriteComment(member,comment);

        writeCommentRepository.save(writeComment);

        return comment;
    }

    @Transactional(readOnly = true)
    public Comment findById(Long commentId){
        return commentRepository.findById(commentId).orElseThrow(
            ()->new NotFoundException(("Comment를 찾을 수 없습니다")));
    }

    @Transactional
    public void updateComment(Long commentId,CommentForm commentForm){
        Comment comment=commentRepository.findById(commentId).orElseThrow(NotFoundException::new);
        comment.edit(commentForm);
    }


    @Transactional
    public void deleteComment(Long commentId){boardRepository.deleteById(commentId);}

    @Transactional
    public Comment createComment(Board board,CommentForm commentForm){
        Comment comment = new Comment(board,commentForm);
        commentRepository.save(comment);

        return comment;
    }

}

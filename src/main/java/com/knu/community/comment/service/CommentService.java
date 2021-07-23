package com.knu.community.comment.service;

import com.knu.community.board.domain.Board;
import com.knu.community.board.repository.BoardRepository;
import com.knu.community.comment.domain.Comment;
import com.knu.community.comment.dto.CommentDto;
import com.knu.community.comment.dto.CommentForm;
import com.knu.community.comment.repository.CommentRepository;
import com.knu.community.error.NotFoundException;
import com.knu.community.member.domain.Member;
import com.knu.community.member.repository.MemberRepository;
import com.knu.community.reply.repository.ReplyRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public Comment writeComment(Long boardId,Long userId, CommentForm commentForm){
        Member member = memberRepository.findById(userId).orElseThrow(()->
            new NotFoundException("Member가 존재하지 않습니다."));
        Board board = boardRepository.findById(boardId).orElseThrow(()->
            new NotFoundException("Comment를 달 수 있는 Board가 존재하지 않습니다."));
        Comment comment = createComment(member,board,commentForm);


        return comment;
    }

    @Transactional(readOnly = true)
    public Comment findById(Long commentId){
        return commentRepository.findById(commentId).orElseThrow(
            ()->new NotFoundException(("Comment를 찾을 수 없습니다")));
    }

    @Transactional
    public boolean updateComment(Long commentId,CommentForm commentForm){
        Comment comment=commentRepository.findById(commentId).orElseThrow(NotFoundException::new);
        comment.edit(commentForm);
        return true;
    }


    @Transactional
    public void deleteComment(Long commentId){
        boardRepository.deleteById(commentId);
    }

    @Transactional
    public Comment createComment(Member member,Board board,CommentForm commentForm){
        Comment comment = new Comment(member,board,commentForm);
        commentRepository.save(comment);

        return comment;
    }

    public List<Comment> findMyComments(Long memId) {
        return commentRepository.findMyComments(memId).orElseThrow(()->
            new NotFoundException("작성한 댓글이 없습니다.")
        );
    }

    public List<CommentDto> findContentsByBoardId(Long boardId) {
        List<Comment> comments = commentRepository.findByBoard_Id(boardId).orElseThrow(() ->
            new NotFoundException("게시물이 존재하지 않습니다.")
        );
        return comments.stream().map(CommentDto::new).collect(Collectors.toList());
    }
}

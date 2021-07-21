package com.knu.community.comment.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.knu.community.base.BaseTimeEntity;
import com.knu.community.board.domain.Board;
import com.knu.community.comment.dto.CommentForm;
import com.knu.community.member.domain.Member;
import com.knu.community.reply.domain.Reply;
import java.time.LocalDateTime;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Comment extends BaseTimeEntity {

    @Id
    @GeneratedValue
    @Column(name="comment_id")
    private Long id;

    private String content;

    private String author;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="board_id")
    @JsonIgnore
    private Board board;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="member_id")
    @JsonIgnore
    private Member member;


    @OneToMany(mappedBy = "comment")
    private List<Reply> replyList = new ArrayList<>();


    public Comment(Member member,Board board,CommentForm commentForm){
        this.author=commentForm.getAuthor();
        this.content=commentForm.getContent();
        setBoard(board);
        setMember(member);
    }

    public void edit(CommentForm commentForm){
        content = changedInfo(content, commentForm.getContent());
        author = changedInfo(author, commentForm.getAuthor());
    }

    private String changedInfo(String original, String changed){
        return (changed == null || changed.equals("")) ? original : changed;
    }

    public void setBoard(Board board){
        if(this.board!=null){
            this.board.getCommentList().remove(this);
        }
        this.board=board;
        board.getCommentList().add(this);
    }

    public void setMember(Member member){
        if(this.member!=null){
            this.member.getCommentList().remove(this);
        }
        this.member=member;
        board.getCommentList().add(this);
    }
}

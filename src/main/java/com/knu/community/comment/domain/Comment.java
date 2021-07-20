package com.knu.community.comment.domain;


import com.knu.community.base.BaseTimeEntity;
import com.knu.community.board.domain.Board;
import com.knu.community.comment.dto.CommentForm;
import com.knu.community.reply.domain.Reply;
import java.time.LocalDateTime;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import lombok.AllArgsConstructor;
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

    private LocalDateTime dateTime;

    @ManyToOne
    @JoinColumn(name="board_id")
    private Board board;

    @OneToMany(mappedBy = "comment")
    private List<WriteComment> commenterList;

    @OneToMany(mappedBy = "comment")
    private List<Reply> replyList;


    public Comment(Board board,CommentForm commentForm){
        this.author=commentForm.getAuthor();
        this.content=commentForm.getContent();
        setBoard(board);
    }

    public void edit(CommentForm commentForm){
        content = changedInfo(content, commentForm.getContent());
        author = changedInfo(author, commentForm.getAuthor());
        dateTime = LocalDateTime.now();
    }

    private String changedInfo(String original, String changed){
        return (changed == null || changed.equals("")) ? original : changed;
    }

    public void setBoard(Board board){
        if(board.getId()!=null){
            this.board.getCommentList().remove(this);
        }
        this.board=board;
        board.getCommentList().add(this);
    }
}

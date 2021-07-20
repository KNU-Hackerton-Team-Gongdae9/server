package com.knu.community.reply.domain;


import com.knu.community.base.BaseTimeEntity;
import com.knu.community.comment.domain.Comment;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.knu.community.reply.dto.ReplyForm;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class Reply extends BaseTimeEntity {


    @Id
    @GeneratedValue
    @Column(name="reply_id")
    private Long id;

    private String content;

    private String author;

    private LocalDateTime dateTime;

    @ManyToOne
    @JoinColumn(name="comment_id")
    private Comment comment;

    public void setComment(Comment comment){
        if(comment.getId()!=null){
            this.comment.getReplyList().remove(this);
        }
        this.comment=comment;
        comment.getReplyList().add(this);
    }

    public void edit(ReplyForm replyForm){
        content = changedInfo(content, replyForm.getContent());
        author = changedInfo(author, replyForm.getAuthor());
        dateTime = LocalDateTime.now();
    }

    private String changedInfo(String original, String changed){
        return (changed == null || changed.equals("")) ? original : changed;
    }

    public static Reply createInstance(Comment comment, ReplyForm replyForm){
        Reply reply = Reply.builder()
                .content(comment.getContent())
                .author(comment.getAuthor())
                .dateTime(LocalDateTime.now())
                .build();
        reply.setComment(comment);
        return reply;
    }
}


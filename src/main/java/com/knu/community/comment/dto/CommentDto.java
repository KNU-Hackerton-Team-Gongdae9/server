package com.knu.community.comment.dto;


import com.knu.community.comment.domain.Comment;
import com.knu.community.reply.domain.Reply;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Getter;

@Getter
public class CommentDto {

    private Long commentId;

    private String author;

    private String content;

    private List<Reply> replyList;

    private LocalDateTime time;

    public CommentDto(Comment comment){
        this.commentId = getCommentId();
        this.author=comment.getAuthor();
        this.content=comment.getContent();
        this.replyList = comment.getReplyList();
        this.time=comment.getLastModifiedDate();
    }
}

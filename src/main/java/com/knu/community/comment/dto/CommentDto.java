package com.knu.community.comment.dto;


import com.knu.community.comment.domain.Comment;
import com.knu.community.reply.domain.Reply;
import com.knu.community.reply.dto.ReplyDto;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Getter;

@Getter
public class CommentDto {

    private Long commentId;

    private String author;

    private String content;

    private List<ReplyDto> replyDtoList;

    private LocalDateTime time;

    public CommentDto(Comment comment){
        this.commentId = comment.getId();
        this.author=comment.getAuthor();
        this.content=comment.getContent();
        this.replyDtoList = comment.getReplyList().stream().map(ReplyDto::new).collect(Collectors.toList());
        this.time=comment.getLastModifiedDate();
    }
}

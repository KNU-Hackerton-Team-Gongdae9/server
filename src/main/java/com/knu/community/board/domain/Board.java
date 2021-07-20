package com.knu.community.board.domain;


import com.knu.community.base.BaseTimeEntity;
import com.knu.community.board.dto.BoardForm;
import com.knu.community.comment.domain.Comment;
import com.knu.community.reply.dto.ReplyForm;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor @AllArgsConstructor
@Builder
@Getter
public class Board extends BaseTimeEntity {

    @Id
    @GeneratedValue
    @Column(name="board_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    private Category category;

    private String title;

    private String content;

    private String author;

    private LocalDateTime dateTime;

    @OneToMany(mappedBy="board")
    private List<WriteBoard> writerList;

    @OneToMany(mappedBy="board")
    private List<Comment> commentList;

    public void edit(BoardForm boardForm){
        content = changedInfo(content, boardForm.getContent());
        author = changedInfo(author, boardForm.getAuthor());
        dateTime = LocalDateTime.now();
    }

    private String changedInfo(String original, String changed){
        return (changed == null || changed.equals("")) ? original : changed;
    }


    public static Board createBoard(BoardForm boardForm){
        return Board.builder()
            .category(boardForm.getCategory())
            .title(boardForm.getTitle())
            .content(boardForm.getContent())
            .author(boardForm.getAuthor())
            .dateTime(LocalDateTime.now())
            .build();
    }
}
